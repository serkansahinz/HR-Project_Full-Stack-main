package com.hrproject.service;

import com.hrproject.dto.request.EmployeeUpdateRequestDto;
import com.hrproject.dto.request.ManagerUpdateEmployeeRequestDto;
import com.hrproject.dto.response.UserProfileFindAllResponseDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import com.hrproject.exception.ErrorType;
import com.hrproject.exception.UserManagerException;

import com.hrproject.mapper.IUserMapper;
import com.hrproject.rabbitmq.model.CreateEmployeeAuthModel;
import com.hrproject.rabbitmq.model.RegisterModel;

import com.hrproject.rabbitmq.model.SendCompanyIdToManagerModel;

import com.hrproject.repository.IUserRepository;
import com.hrproject.repository.entity.UserProfile;
import com.hrproject.repository.enums.EGender;
import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EShift;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.utility.AvatarService;
import com.hrproject.utility.JwtTokenManager;
import com.hrproject.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<UserProfile, String> {
  private final IUserRepository userRepository;

  private final JwtTokenManager jwtTokenManager;
  private final AvatarService avatarService;

  public UserService(IUserRepository userRepository,JwtTokenManager jwtTokenManager,AvatarService avatarService) {
	super(userRepository);
	this.userRepository = userRepository;

	this.jwtTokenManager = jwtTokenManager;
	this.avatarService = avatarService;
  }

  public Boolean createNewUserWithRabbitmq(RegisterModel model) {
	try {

	  UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(model);
	  userProfile.setRole(ERole.valueOf(model.getRole().toUpperCase()));
	  if (model.getRole().equals("COMPANY_MANAGER")) {
		userProfile.setGender(EGender.valueOf(model.getGender().toUpperCase()));
		if (userProfile.getGender().equals(EGender.ERKEK)) {
		  userProfile.setAvatar("https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/man5-512.png");
		} else {
		  userProfile.setAvatar("https://cdn2.iconfinder.com/data/icons/office-and-business-special-set-1/260/19-512.png");
		}
	  } else {
		userProfile.setAvatar("https://cdn1.iconfinder.com/data/icons/circle-flats/170/contacts-512.png");
	  }

	  save(userProfile);

	  return true;
	} catch (Exception e) {
	  throw new UserManagerException(ErrorType.USER_NOT_CREATED);
	}
  }

  public String activateStatus(String token) {
	Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(token);
	if (authId.isEmpty()) {
	  throw new UserManagerException(ErrorType.INVALID_TOKEN);
	}
	Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}
	userProfile.get().setStatus(EStatus.ACTIVE);
	update(userProfile.get());
	return "Hesabınız aktive olmuştur";
  }

  public Boolean createEmployee(CreateEmployeeAuthModel model) {
	UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(model);
	userProfile.setRole(ERole.EMPLOYEE);
	userProfile.setStatus(EStatus.ACTIVE);
	userProfile.setAuthId(model.getAuthId());
	userProfile.setShift(EShift.valueOf(model.getShift().toUpperCase()));
	userProfile.setGender(EGender.valueOf(model.getGender().toUpperCase()));
	userProfile.setEmail(model.getEmail());

	if (userProfile.getGender().equals(EGender.ERKEK)) {
	  userProfile.setAvatar("https://cdn3.iconfinder.com/data/icons/avatars-round-flat/33/man5-512.png");
	} else {
	  userProfile.setAvatar("https://cdn2.iconfinder.com/data/icons/office-and-business-special-set-1/260/19-512.png");
	}
	save(userProfile);

	return true;
  }

  public List<UserProfileResponseDto> findUserProfileByCompanyId(String companyId) {

	return IUserMapper.INSTANCE.toUserProfileResponseDtoList(userRepository.findEmployeesByCompanyId(companyId));
  }

  public void deleteByAuthId(Long id) {
	UserProfile userProfile = userRepository.findByAuthId(id).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	userProfile.setStatus(EStatus.DELETED);
	update(userProfile);
  }

  public UserProfileResponseDto findAuthById(Long authId) {

	UserProfile userProfile = userRepository.findByAuthId(authId).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	return IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile);
  }

  public UserProfileResponseDto employeeUpdateProfile(String id,EmployeeUpdateRequestDto dto) {
	UserProfile userProfile = findById(id).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	userProfile.setPhone(dto.getPhone());
	userProfile.setProvince(dto.getProvince());
	userProfile.setStreet(dto.getStreet());

	update(userProfile);
	return IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile);
  }

  public UserProfileResponseDto managerUpdateEmployee(String id,ManagerUpdateEmployeeRequestDto dto) {
	UserProfile userProfile = findById(id).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	userProfile.setSalary(dto.getSalary());
	userProfile.setDepartment(dto.getDepartment());
	userProfile.setShift(EShift.valueOf(dto.getShift()));
	update(userProfile);
	return IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile);
  }

  public List<UserProfileFindAllResponseDto> findAllUserProfile() {
	List<UserProfile> userProfileList = findAll();
	return userProfileList.stream().map(IUserMapper.INSTANCE::toUserProfileFindAllResponseDto).toList();
  }

  public boolean deleteCompanyManagerById(Long id) {
	UserProfile userProfile = userRepository.findByAuthId(id).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	userProfile.setStatus(EStatus.DELETED);
	update(userProfile);
	return true;
  }

  public void sendCompanyIdToManager(SendCompanyIdToManagerModel model) {

	UserProfile userProfile = userRepository.findByAuthId(model.getCompanyManagerId()).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	userProfile.setCompanyId(model.getCompanyId());
	update(userProfile);
  }


  public double totalSalary(String companyId) {
	List<UserProfile> userProfileList = userRepository.findEmployeesByCompanyId(companyId);
	double totalSalary = 0;
	for (UserProfile userProfile : userProfileList) {
	  if (userProfile.getSalary() != null) {
		totalSalary += Double.parseDouble(userProfile.getSalary());
	  }
	}

	return totalSalary;
  }

  public UserProfileResponseDto employeeUpdateAvatar(String id,MultipartFile file) {
	UserProfile userProfile = findById(id).orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
	String avatarUrl = avatarService.uploadAvatar(file);
	userProfile.setAvatar(avatarUrl);
	update(userProfile);
	return IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile);
  }
}
