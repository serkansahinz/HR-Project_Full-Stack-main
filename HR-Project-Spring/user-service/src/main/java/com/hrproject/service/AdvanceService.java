package com.hrproject.service;

import com.hrproject.dto.request.AdvanceRequestDto;
import com.hrproject.dto.response.AdvanceResponseDto;
import com.hrproject.mapper.IAdvanceMapper;
import com.hrproject.repository.IAdvanceRepository;
import com.hrproject.repository.entity.Advance;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AdvanceService extends ServiceManager<Advance, String> {

  private final IAdvanceRepository advanceRepository;

  public AdvanceService(IAdvanceRepository advanceRepository) {
	super(advanceRepository);
	this.advanceRepository = advanceRepository;
  }

  public String createAdvance(String companyId,String userId,AdvanceRequestDto dto) {
	Advance advance = Advance.builder()
							 .amount(dto.getAmount())
							 .companyId(companyId)
							 .userId(userId)
							 .fullName(dto.getName() + " " + dto.getSurname())
							 .build();
	save(advance);
	return "Advance created successfully";
  }

  public List<AdvanceResponseDto> findPendingAdvance(String companyId) {
	return IAdvanceMapper.INSTANCE.toAdvanceResponseDtoList(advanceRepository.findPendingAdvancesByCompanyId(companyId));
  }


  public List<AdvanceResponseDto> findAllAdvanceByUserId(String userId) {
	List<Advance> advances = advanceRepository.findAllByUserId(userId);
	List<AdvanceResponseDto> dtos = IAdvanceMapper.INSTANCE.toAdvanceResponseDtoList(advances);

	for (int i = 0; i < dtos.size(); i++) {
	  dtos.get(i).setUpdatedDate(convertMillisToLocalDate(advances.get(i).getUpdateDate()));
	  dtos.get(i).setCreatedDate(convertMillisToLocalDate(advances.get(i).getCreateDate()));
	}
	return dtos;
  }

  public static LocalDate convertMillisToLocalDate(long millis) {
	Instant instant = Instant.ofEpochMilli(millis);
	return instant.atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public void deleteAdvanceById(String id) {
	Advance advance = findById(id).orElseThrow(() -> new RuntimeException("Advance not found"));
	advance.setStatus(EStatus.REJECTED);
	update(advance);
  }

  //activate status
  public void activateAdvanceById(String id) {
	Advance advance = findById(id).orElseThrow(() -> new RuntimeException("Advance not found"));
	advance.setStatus(EStatus.ACTIVE);
	update(advance);
  }
}
