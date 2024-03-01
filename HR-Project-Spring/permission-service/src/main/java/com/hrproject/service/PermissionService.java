package com.hrproject.service;

import com.hrproject.dto.request.PermissionRequestDto;
import com.hrproject.dto.response.PermissionResponseDto;
import com.hrproject.exception.ErrorType;
import com.hrproject.exception.PermissionManagerException;
import com.hrproject.mapper.IPermissionMapper;
import com.hrproject.repository.IPermissionRepository;
import com.hrproject.repository.entity.Permission;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import com.hrproject.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class PermissionService extends ServiceManager<Permission, String> {
  private final IPermissionRepository permissionRepository;

  private final UserPermissionService userPermissionService;


  public PermissionService(IPermissionRepository permissionRepository,UserPermissionService userPermissionService) {
	super(permissionRepository);
	this.permissionRepository = permissionRepository;

	this.userPermissionService = userPermissionService;

  }

  public void createPermission(PermissionRequestDto dto) {
	Permission permission = Permission.builder()
									  .type(EType.valueOf(dto.getType().toUpperCase()))
									  .companyId(dto.getCompanyId())
									  .startDate(dto.getStartDate())
									  .endDate(dto.getEndDate())
									  .fullName(dto.getName() + " " + dto.getSurname())
									  .userId(dto.getUserId())
									  .gender(dto.getGender())
									  .build();

	if (permission.getStartDate().isBefore(LocalDate.now()) || permission.getStartDate().isAfter(permission.getEndDate())) {
	  throw new PermissionManagerException(ErrorType.BAD_REQUEST,"Başlangıç tarihi bugünden önce veya bitiş tarihinden sonra olamaz");
	}

	if (permission.getGender().equals("ERKEK") && (permission.getType().equals(EType.ANNELİK) || permission.getType().equals(EType.GEBELİK))) {
	  throw new PermissionManagerException(ErrorType.BAD_REQUEST,"Cinsiyet ve izin türü uyuşmuyor");
	}

	if (permission.getGender().equals("KADIN") && (permission.getType().equals(EType.BABALIK))) {
	  throw new PermissionManagerException(ErrorType.BAD_REQUEST,"Cinsiyet ve izin türü uyuşmuyor");
	}

	save(permission);
  }
  //activate status of permission

  public void activatePermission(String id) {
	Permission permission = findById(id).orElseThrow(() -> new PermissionManagerException(ErrorType.PERMISSION_NOT_FOUND));
	boolean result = userPermissionService.createUserPermission(id,permission,permission.getGender());
	if (result) {
	  permission.setStatus(EStatus.ACTIVE);
	  update(permission);
	} else {
	  permission.setStatus(EStatus.REJECTED);
	  update(permission);
	  throw new PermissionManagerException(ErrorType.BAD_REQUEST,"Girilen gün sayısı hatalı");
	}
  }

  public List<PermissionResponseDto> findPendingPermissionsByCompanyId(String companyId) {

	return IPermissionMapper.INSTANCE.toPermissionResponseDtoList(permissionRepository.findPendingPermissionsByCompanyId(companyId));
  }

  public String deletePermission(String id) {
	Permission permission = findById(id).orElseThrow(() -> new PermissionManagerException(ErrorType.PERMISSION_NOT_FOUND));
	permission.setStatus(EStatus.REJECTED);
	update(permission);
	return "İzin silindi";
  }

  public List<PermissionResponseDto> findPermissionsByUserId(String userId) {

	List<Permission> permissions = permissionRepository.findPermissionsByUserId(userId);
	List<PermissionResponseDto> dto = IPermissionMapper.INSTANCE.toPermissionResponseDtoList(permissions);

	for (int i = 0; i < dto.size(); i++) {
	  dto.get(i).setUpdatedDate(convertMillisToLocalDate(permissions.get(i).getUpdateDate()));
	  dto.get(i).setCreatedDate(convertMillisToLocalDate(permissions.get(i).getCreateDate()));
	}

	return dto;
  }

  public static LocalDate convertMillisToLocalDate(long millis) {
	Instant instant = Instant.ofEpochMilli(millis);
	return instant.atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
