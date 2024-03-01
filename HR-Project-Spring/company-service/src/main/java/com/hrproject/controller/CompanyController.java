package com.hrproject.controller;

import com.hrproject.dto.request.CompanyUpdateRequestDto;
import com.hrproject.dto.response.CompanyResponseDto;
import com.hrproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
@CrossOrigin

public class CompanyController {
  private final CompanyService companyService;

  @GetMapping(ACTIVATE_STATUS + "/{token}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Boolean> activateCompanyStatus(@PathVariable String token) {
	return ResponseEntity.ok(companyService.activateStatus(token));
  }

  @GetMapping(PENDING_COMPANIES)
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<CompanyResponseDto>> findAllPendingCompany() {
	return ResponseEntity.ok(companyService.findAllPendingCompany());
  }

  @DeleteMapping(DELETE_BY_ID + "/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Boolean> deleteCompanyById(@PathVariable String id) {
	return ResponseEntity.ok(companyService.deleteCompanyById(id));
  }

  @GetMapping(FIND_COMPANY_BY_ID + "/{id}")
  public ResponseEntity<CompanyResponseDto> findCompanyById(@PathVariable String id) {
	return ResponseEntity.ok(companyService.findCompanyById(id));
  }

  @GetMapping(FIND_ALL_ACTIVE_COMPANIES)
  public ResponseEntity<List<CompanyResponseDto>> findAllActiveCompanies() {
	return ResponseEntity.ok(companyService.findAllActiveCompanies());
  }

  @PutMapping(UPDATE_COMPANY + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Boolean> updateCompany(@PathVariable String companyId,@RequestBody CompanyUpdateRequestDto dto) {
	return ResponseEntity.ok(companyService.companyUpdate(companyId,dto));
  }

  //getRemainingDays
  @GetMapping(GET_REMAINING_DAYS + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Integer> getRemainingDays(@PathVariable String companyId) {
	return ResponseEntity.ok(companyService.getRemainingDays(companyId));
  }
}
