package com.hrproject.controller;

import com.hrproject.dto.request.*;
import com.hrproject.dto.response.RegisterResponseDto;
import com.hrproject.repository.entity.Auth;
import com.hrproject.service.AuthService;
import com.hrproject.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hrproject.constant.EndPoints.*;
import static com.hrproject.constant.EndPoints.CREATE_EMPLOYEE;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
  private final AuthService authService;
  private final JwtTokenManager jwtTokenManager;

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
	return ResponseEntity.ok("Hello from auth service");
  }

  @PostMapping(CREATE_EMPLOYEE)
  @PreAuthorize(" hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Boolean> createEmployee(@RequestBody CreateEmployeeRequestDto dto) {

	return ResponseEntity.ok(authService.createEmployee(dto));
  }

  @PostMapping(REGISTER + "_guest")
  public ResponseEntity<RegisterResponseDto> guestRegister(@RequestBody @Valid RegisterRequestDto dto) {
	return ResponseEntity.ok(authService.guestRegister(dto));
  }

  @PostMapping(REGISTER + "_manager")
  public ResponseEntity<RegisterResponseDto> managerRegister(@RequestBody @Valid ManagerRegisterDto dto) {
	return ResponseEntity.ok(authService.managerRegister(dto));
  }

  @PostMapping(LOGIN)
  public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
	return ResponseEntity.ok(authService.login(dto));
  }

  @DeleteMapping(DELETE_BY_ID + "/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Long id) {
	return ResponseEntity.ok(authService.deleteUserById(id));
  }

  @GetMapping(ACTIVATE_STATUS)
  public ResponseEntity<Boolean> activateStatus(@RequestParam String token) {
	return ResponseEntity.ok(authService.activateStatus(token));
  }

  @GetMapping("/create_token")
  public ResponseEntity<String> createToken(Long id) {
	return ResponseEntity.ok(jwtTokenManager.createToken(id).orElseThrow(() -> new RuntimeException("token oluşturulamadı")));
  }

  @GetMapping("/get_id_from_token")
  public ResponseEntity<Long> getIdFromToken(String token) {
	return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new RuntimeException("Geçersiz token")));
  }

  @GetMapping(FIND_ALL)
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<Auth>> findAll() {

	return ResponseEntity.ok(authService.findAll());
  }
}
