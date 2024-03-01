package com.hrproject.controller;

import com.hrproject.dto.request.CreateEmployeeRequestDto;
import com.hrproject.dto.request.EmployeeUpdateRequestDto;
import com.hrproject.dto.request.ManagerUpdateEmployeeRequestDto;
import com.hrproject.dto.response.UserProfileFindAllResponseDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import com.hrproject.repository.entity.UserProfile;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.service.UserService;
import com.hrproject.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
  private final UserService userService;

  @GetMapping(FIND_EMPLOYEE_BY_COMPANY_ID + "/{companyId}")
  public ResponseEntity<List<UserProfileResponseDto>> findUserProfileByCompanyId(@PathVariable String companyId) {
	return ResponseEntity.ok(userService.findUserProfileByCompanyId(companyId));
  }

  @PostMapping(ACTIVATE_STATUS)
  public ResponseEntity<String> activateStatus(@RequestParam String token) {
	return ResponseEntity.ok(userService.activateStatus(token));
  }

  @GetMapping(FIND_ALL)
  public ResponseEntity<List<UserProfileFindAllResponseDto>> findAll() {
	return ResponseEntity.ok(userService.findAllUserProfile());
  }

  @GetMapping("/find_by_auth_id/{authId}")
  public ResponseEntity<UserProfileResponseDto> findByUserWithAuthId(@PathVariable Long authId) {
	return ResponseEntity.ok(userService.findAuthById(authId));
  }

  @PutMapping(MANAGER_UPDATE_EMPLOYEE + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<UserProfileResponseDto> managerUpdateEmployee(@PathVariable String id,@RequestBody ManagerUpdateEmployeeRequestDto dto) {
	return ResponseEntity.ok(userService.managerUpdateEmployee(id,dto));
  }

  @PutMapping(EMPLOYEE_UPDATE_PROFILE + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER') || hasAuthority('EMPLOYEE')")
  public ResponseEntity<UserProfileResponseDto> employeeUpdateProfile(@PathVariable String id,@RequestBody EmployeeUpdateRequestDto dto) {

	return ResponseEntity.ok(userService.employeeUpdateProfile(id,dto));
  }

//  @PutMapping(EMPLOYEE_UPDATE_AVATAR + "/{id}")

  @PutMapping(EMPLOYEE_UPDATE_AVATAR + "/{id}")
  public ResponseEntity<UserProfileResponseDto> employeeUpdateAvatar(@PathVariable String id,@RequestParam MultipartFile file) throws IOException {

	return ResponseEntity.ok(userService.employeeUpdateAvatar(id,file));
  }

  @GetMapping("/total_salary/{companyId}")
  public double totalSalary(@PathVariable String companyId) {
	return userService.totalSalary(companyId);
  }
}
