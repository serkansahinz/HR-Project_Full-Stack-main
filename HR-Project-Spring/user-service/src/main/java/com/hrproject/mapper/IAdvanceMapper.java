package com.hrproject.mapper;

import com.hrproject.dto.request.CreateEmployeeRequestDto;
import com.hrproject.dto.response.AdvanceResponseDto;
import com.hrproject.dto.response.UserProfileFindAllResponseDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import com.hrproject.rabbitmq.model.CreateEmployeeAuthModel;
import com.hrproject.rabbitmq.model.RegisterElasticModel;
import com.hrproject.rabbitmq.model.RegisterModel;
import com.hrproject.repository.entity.Advance;
import com.hrproject.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdvanceMapper {
  IAdvanceMapper INSTANCE = Mappers.getMapper(IAdvanceMapper.class);

  List<AdvanceResponseDto> toAdvanceResponseDtoList(List<Advance> advances);

}

