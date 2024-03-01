package com.hrproject.mapper;

import com.hrproject.dto.response.PermissionResponseDto;
import com.hrproject.repository.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPermissionMapper {
  IPermissionMapper INSTANCE = Mappers.getMapper(IPermissionMapper.class);

  List<PermissionResponseDto> toPermissionResponseDtoList(List<Permission> permission);


}

