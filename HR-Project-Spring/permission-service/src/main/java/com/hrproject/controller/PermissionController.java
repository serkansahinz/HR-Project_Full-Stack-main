package com.hrproject.controller;

import com.hrproject.dto.request.PermissionRequestDto;
import com.hrproject.dto.response.PermissionResponseDto;
import com.hrproject.dto.response.UserPermissionResponseDto;
import com.hrproject.repository.entity.UserPermission;
import com.hrproject.service.PermissionService;
import com.hrproject.service.UserPermissionService;
import com.hrproject.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(PERMISSION)
@RequiredArgsConstructor
@CrossOrigin
public class PermissionController {
  private final PermissionService permissionService;
  private final UserPermissionService userPermissionService;
  private final JwtTokenManager jwtTokenManager;

  @PostMapping(CREATE_PERMISSION)
  @PreAuthorize("hasAuthority('COMPANY_MANAGER') || hasAuthority('EMPLOYEE')")
  public void createPermission(@RequestBody PermissionRequestDto dto) {
	permissionService.createPermission(dto);
  }

  @PostMapping(ACTIVATE_STATUS + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public void activateStatus(@PathVariable String id) {
	permissionService.activatePermission(id);
  }

  @GetMapping(FIND_PENDING_PERMISSIONS_BY_COMPANY_ID + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<List<PermissionResponseDto>> findPendingPermissionsByCompanyId(@PathVariable String companyId) {
	return ResponseEntity.ok(permissionService.findPendingPermissionsByCompanyId(companyId));
  }

  @DeleteMapping(DELETE_BY_ID + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Void> deleteById(@PathVariable String id) {
	permissionService.deletePermission(id);
	return ResponseEntity.ok().build();
  }

  @GetMapping(FIND_PERMISSIONS_BY_USER_ID + "/{userId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER') || hasAuthority('EMPLOYEE')")
  public ResponseEntity<List<PermissionResponseDto>> findPermissionsByUserId(@PathVariable String userId) {
	return ResponseEntity.ok(permissionService.findPermissionsByUserId(userId));
  }

  @GetMapping(FIND_REMAINING_PERMISSIONS_BY_USER_ID + "/{userId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER') || hasAuthority('EMPLOYEE')")
  public ResponseEntity<List<UserPermissionResponseDto>> findRemainingPermissionsByUserId(@PathVariable String userId,@RequestParam String gender) {
	return ResponseEntity.ok(userPermissionService.findRemainingPermissionOfUser(userId,gender));
  }
}
