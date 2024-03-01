package com.hrproject.service;

import com.hrproject.dto.request.CompanyUpdateRequestDto;
import com.hrproject.dto.response.CompanyResponseDto;
import com.hrproject.exception.ErrorType;
import com.hrproject.exception.CompanyManagerException;
import com.hrproject.mapper.ICompanyMapper;
import com.hrproject.rabbitmq.model.CompanyRegisterModel;
import com.hrproject.rabbitmq.model.SendCompanyIdToManagerModel;
import com.hrproject.rabbitmq.producer.*;
import com.hrproject.repository.ICompanyRepository;
import com.hrproject.repository.entity.Company;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.utility.JwtTokenManager;
import com.hrproject.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company, String> {
  private final ICompanyRepository companyRepository;
  private final JwtTokenManager jwtTokenManager;
  private final CompanyActivationProducer activationProducer;
  private final DeleteCompanyProducer deleteCompanyProducer;
  private final DeleteCompanyUserProducer deleteCompanyUserProducer;
  private final CompanyMailActivationProducer companyMailActivationProducer;
  private final SendCompanyIdToManagerProducer sendCompanyIdToManagerProducer;
  private final DeleteCompanyMailProducer deleteCompanyMailProducer;

  public CompanyService(ICompanyRepository companyRepository,JwtTokenManager jwtTokenManager,CompanyActivationProducer activationProducer,DeleteCompanyProducer deleteCompanyProducer,DeleteCompanyUserProducer deleteCompanyUserProducer,CompanyMailActivationProducer companyMailActivationProducer,SendCompanyIdToManagerProducer sendCompanyIdToManagerProducer,DeleteCompanyMailProducer deleteCompanyMailProducer) {
	super(companyRepository);
	this.companyRepository = companyRepository;
	this.jwtTokenManager = jwtTokenManager;
	this.activationProducer = activationProducer;

	this.deleteCompanyProducer = deleteCompanyProducer;
	this.deleteCompanyUserProducer = deleteCompanyUserProducer;
	this.companyMailActivationProducer = companyMailActivationProducer;
	this.sendCompanyIdToManagerProducer = sendCompanyIdToManagerProducer;
	this.deleteCompanyMailProducer = deleteCompanyMailProducer;
  }

  public boolean createNewCompany(CompanyRegisterModel model) {
	Company company = ICompanyMapper.INSTANCE.toCompany(model);
	if (companyRepository.existsByCompanyTaxNumber(company.getCompanyTaxNumber())) {
	  throw new CompanyManagerException(ErrorType.COMPANY_ALREADY_EXISTS);
	}
	save(company);
	SendCompanyIdToManagerModel modelToUser = SendCompanyIdToManagerModel.builder().companyManagerId(company.getCompanyManagerId()).companyId(company.getId()).build();

	sendCompanyIdToManagerProducer.sendCompanyIdToManager(modelToUser);
	return true;
  }

  public Boolean activateStatus(String token) {
	Optional<Long> companyManagerId = jwtTokenManager.getCompanyManagerIdFromToken(token);
	if (companyManagerId.isPresent()) {
	  Company company = companyRepository.findCompanyByCompanyManagerId(companyManagerId.get()).orElseThrow(() -> new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND));
	  company.setStatus(EStatus.ACTIVE);
	  update(company);
	  activationProducer.activateStatus(token); // rabbitmq ile haberleşme
	  companyMailActivationProducer.companyActivationMail(company.getCompanyManagerMail());
	  //TODO BURADA admin onayladıktan sonra databasedeki token silinecek
	  return true;
	}
	throw new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND);
  }

  public List<CompanyResponseDto> findAllPendingCompany() {

	return ICompanyMapper.INSTANCE.toCompanyResponseDtoList(companyRepository.findCompaniesWithPendingStatus());
  }

  public List<CompanyResponseDto> findAllActiveCompanies() {

	return ICompanyMapper.INSTANCE.toCompanyResponseDtoList(companyRepository.findCompaniesWithActiveStatus());
  }

  public boolean deleteCompanyById(String id) {
	Company company = findById(id).orElseThrow(() -> new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND));
	company.setStatus(EStatus.DELETED);
	update(company);
	deleteCompanyProducer.deleteCompany(company.getCompanyManagerId());
	deleteCompanyUserProducer.deleteCompanyUser(company.getCompanyManagerId());
	deleteCompanyMailProducer.deleteCompanyMail(company.getCompanyManagerMail());
	return true;
  }

  public CompanyResponseDto findCompanyById(String id) {
	Company company = findById(id).orElseThrow(() -> new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND));
	return ICompanyMapper.INSTANCE.toCompanyResponseDto(company);
  }

  public boolean companyUpdate(String companyId,CompanyUpdateRequestDto dto) {
	Company company = findById(companyId).orElseThrow(() -> new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND));

	company.setCompanyEmail(dto.getCompanyEmail());
	company.setCompanyPhone(dto.getCompanyPhone());
	company.setProvince(dto.getProvince());
	company.setStreet(dto.getStreet());
	update(company);
	return true;
  }

  public int getRemainingDays(String companyId) {
	Company company = findById(companyId).orElseThrow(() -> new CompanyManagerException(ErrorType.COMPANY_NOT_FOUND));
	return company.getRemainingDays() - (LocalDate.now().getDayOfYear() - convertMillisToLocalDate(company.getCreateDate()).getDayOfYear());
  }

  public static LocalDate convertMillisToLocalDate(long millis) {
	Instant instant = Instant.ofEpochMilli(millis);
	return instant.atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
