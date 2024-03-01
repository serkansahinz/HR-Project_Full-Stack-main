package com.hrproject.mapper;

import com.hrproject.dto.request.CreateEmployeeRequestDto;
import com.hrproject.dto.request.EmployeeUpdateRequestDto;
import com.hrproject.dto.response.UserProfileFindAllResponseDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import com.hrproject.rabbitmq.model.CreateEmployeeAuthModel;
import com.hrproject.rabbitmq.model.RegisterElasticModel;
import com.hrproject.rabbitmq.model.RegisterModel;
import com.hrproject.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  UserProfile toUserProfile(CreateEmployeeRequestDto dto);

  UserProfile toUserProfile(RegisterModel model);



  // @Mapping( source = "id",target = "userProfileId")
  UserProfileFindAllResponseDto toUserProfileFindAllResponseDto(UserProfile userProfile);

  RegisterElasticModel toRegisterElasticModel(UserProfile userProfile);

  UserProfileResponseDto toUserProfileResponseDto(UserProfile userProfile);

  CreateEmployeeAuthModel toCreateEmployeeAuthModel(UserProfile userProfile);

  List<UserProfileResponseDto> toUserProfileResponseDtoList(List<UserProfile> userProfileList);

  UserProfile toUserProfile(CreateEmployeeAuthModel model);
}

