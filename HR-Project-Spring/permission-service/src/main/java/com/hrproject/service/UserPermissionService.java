package com.hrproject.service;

import com.hrproject.dto.response.UserPermissionResponseDto;
import com.hrproject.repository.IPermissionRepository;
import com.hrproject.repository.IUserPermissionRepository;
import com.hrproject.repository.entity.Permission;
import com.hrproject.repository.entity.UserPermission;
import com.hrproject.repository.enums.EType;
import com.hrproject.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPermissionService extends ServiceManager<UserPermission, String> {
  private final IUserPermissionRepository userPermissionRepository;

  public UserPermissionService(MongoRepository<UserPermission, String> repository,IUserPermissionRepository userPermissionRepository,IPermissionRepository permissionRepository) {
	super(repository);
	this.userPermissionRepository = userPermissionRepository;
  }

  public boolean createUserPermission(String id,Permission permission,String gender) {
	UserPermission userPermission = userPermissionRepository.findUserPermissionByUserId(permission.getUserId());


	if (userPermission == null) {
	  if (gender.equals("ERKEK")) {
		int izinAlinanGun = permission.getEndDate().getDayOfYear() - permission.getStartDate().getDayOfYear();
		if (izinAlinanGun < permission.getType().getKalanGün()) {
		  UserPermission newUserPermission = UserPermission.builder().userId(permission.getUserId()).fullName(permission.getFullName()).remainingPermissions(new EnumMap<>(EType.class) {{
			put(EType.BABALIK,EType.BABALIK.getKalanGün());
			put(EType.YILLIK,EType.YILLIK.getKalanGün());
		  }}).build();
		  newUserPermission.getRemainingPermissions().put(permission.getType(),permission.getType().getKalanGün() - izinAlinanGun);
		  save(newUserPermission); // Yeni bir UserPermission kaydı oluşturun
		  return true;
		} else {
		  return false;
		}
	  } else {
		int izinAlinanGun = permission.getEndDate().getDayOfYear() - permission.getStartDate().getDayOfYear();
		if (izinAlinanGun < permission.getType().getKalanGün()) {
		  UserPermission newUserPermission = UserPermission.builder().userId(permission.getUserId()).fullName(permission.getFullName()).remainingPermissions(new EnumMap<>(EType.class) {{
			put(EType.ANNELİK,EType.ANNELİK.getKalanGün());
			put(EType.GEBELİK,EType.GEBELİK.getKalanGün());
			put(EType.YILLIK,EType.YILLIK.getKalanGün());
		  }}).build();
		  newUserPermission.getRemainingPermissions().put(permission.getType(),permission.getType().getKalanGün() - izinAlinanGun);
		  save(newUserPermission); // Yeni bir UserPermission kaydı oluşturun
		  return true;
		} else {
		  return false;
		}
	  }
	} else {
	  int izinAlinanGun = permission.getEndDate().getDayOfYear() - permission.getStartDate().getDayOfYear();

	  if (izinAlinanGun > userPermission.getRemainingPermissions().get(permission.getType())) {
		return false;
	  } else {
		userPermission.getRemainingPermissions().put(permission.getType(),userPermission.getRemainingPermissions().get(permission.getType()) - izinAlinanGun);
		update(userPermission); // Varolan UserPermission kaydını güncelleyin
		return true;
	  }
	}
  }


  public List<UserPermissionResponseDto> findRemainingPermissionOfUser(String userId,String gender) {
	UserPermission userPermission = userPermissionRepository.findUserPermissionByUserId(userId);
	List<UserPermissionResponseDto> permissionList = new ArrayList<>();

	if (userPermission == null) {
	  Map<EType, Integer> remainingPermissionsMap = new EnumMap<>(EType.class);

	  if (gender.equals("ERKEK")) {
		remainingPermissionsMap.put(EType.BABALIK,EType.BABALIK.getKalanGün());
		remainingPermissionsMap.put(EType.YILLIK,EType.YILLIK.getKalanGün());
	  } else {
		remainingPermissionsMap.put(EType.ANNELİK,EType.ANNELİK.getKalanGün());
		remainingPermissionsMap.put(EType.GEBELİK,EType.GEBELİK.getKalanGün());
		remainingPermissionsMap.put(EType.YILLIK,EType.YILLIK.getKalanGün());
	  }

	  for (Map.Entry<EType, Integer> entry : remainingPermissionsMap.entrySet()) {
		permissionList.add(UserPermissionResponseDto.builder()
													.type(entry.getKey())
													.remainingDays(entry.getValue())
													.build());
	  }
	} else {
	  for (Map.Entry<EType, Integer> entry : userPermission.getRemainingPermissions().entrySet()) {
		permissionList.add(UserPermissionResponseDto.builder()
													.type(entry.getKey())
													.remainingDays(entry.getValue())
													.build());
	  }
	}

	return permissionList;
  }
}
