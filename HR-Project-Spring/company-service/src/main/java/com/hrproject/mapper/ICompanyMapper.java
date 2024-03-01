package com.hrproject.mapper;

import com.hrproject.dto.response.CompanyResponseDto;
import com.hrproject.rabbitmq.model.CompanyRegisterModel;
import com.hrproject.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {
  ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

  Company toCompany(CompanyRegisterModel model);

  List<CompanyResponseDto> toCompanyResponseDtoList(List<Company> companies);

  CompanyResponseDto toCompanyResponseDto(Company company);
}
