package com.hrproject.controller;

import com.hrproject.dto.request.AdvanceRequestDto;
import com.hrproject.dto.request.EmployeeUpdateRequestDto;
import com.hrproject.dto.request.ManagerUpdateEmployeeRequestDto;
import com.hrproject.dto.response.AdvanceResponseDto;
import com.hrproject.dto.response.UserProfileFindAllResponseDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import com.hrproject.service.AdvanceService;
import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(USER + ADVANCE)
@RequiredArgsConstructor
@CrossOrigin
public class AdvanceController {
  private final AdvanceService advanceService;

  @PostMapping(CREATE_ADVANCE + "/{companyId}/{userId}")
  public ResponseEntity<String> createAdvance(@PathVariable String companyId,@PathVariable String userId,@RequestBody AdvanceRequestDto dto) {
	return ResponseEntity.ok(advanceService.createAdvance(companyId,userId,dto));
  }

  @GetMapping(FIND_PENDING_ADVANCE + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public List<AdvanceResponseDto> findPendingAdvance(@PathVariable String companyId) {
	return advanceService.findPendingAdvance(companyId);
  }
  //findAllAdvanceByUserId

  @GetMapping(FIND_ALL_ADVANCE_BY_USER_ID + "/{userId}")
  public List<AdvanceResponseDto> findAllAdvanceByUserId(@PathVariable String userId) {
	return advanceService.findAllAdvanceByUserId(userId);
  }

  @PutMapping(DELETE_ADVANCE_BY_ID + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Void> deleteAdvanceById(@PathVariable String id) {
	advanceService.deleteAdvanceById(id);
	return ResponseEntity.ok().build();
  }

  @PutMapping(ACTIVATE_ADVANCE_STATUS + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Void> activateAdvanceById(@PathVariable String id) {
	advanceService.activateAdvanceById(id);
	return ResponseEntity.ok().build();
  }
}
