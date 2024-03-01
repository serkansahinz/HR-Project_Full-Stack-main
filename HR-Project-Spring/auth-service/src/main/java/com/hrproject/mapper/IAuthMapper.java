package com.hrproject.mapper;

import com.hrproject.dto.request.CreateEmployeeRequestDto;
import com.hrproject.dto.request.ManagerRegisterDto;
import com.hrproject.dto.request.RegisterRequestDto;
import com.hrproject.dto.request.UserSaveRequestDto;
import com.hrproject.dto.response.RegisterResponseDto;
import com.hrproject.rabbitmq.model.*;
import com.hrproject.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

  IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

  Auth toAuth(RegisterRequestDto dto);

  Auth toAuth(ManagerRegisterDto dto);

  RegisterResponseDto toRegisterResponseDto(Auth auth);

  @Mapping(source = "id", target = "authId")
  UserSaveRequestDto toUserSaveRequestDto(Auth auth);

  @Mapping(source = "id", target = "authId")
  RegisterModel toRegisterModel(Auth auth);

  MailRegisterModel toMailRegisterModel(Auth auth);

  @Mapping(source = "id", target = "authId")
  RegisterModel toRegisterModel(ManagerRegisterDto dto);

  @Mapping(source = "id", target = "authId")
  RegisterModel toRegisterModel(RegisterRequestDto dto);

  @Mapping(source = "id", target = "companyManagerId")
  @Mapping(source = "email", target = "companyManagerMail")
  CompanyRegisterModel toCompanyRegisterModel(ManagerRegisterDto dto);

  Auth toAuth(CreateEmployeeAuthModel model);

  Auth toAuth(CreateEmployeeRequestDto dto);

  CreateEmployeeAuthModel toCreateEmployeeAuthModel(CreateEmployeeRequestDto auth);

  EmployeeMailModel toEmployeeMailModel(CreateEmployeeRequestDto dto);
}
