package com.hrproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

  @GetMapping("/auth_service")
  public ResponseEntity<String> authServiceFallback() {
	return ResponseEntity.ok("Auth Service şuanda hizmet veremiyor!!!");
  }

  @GetMapping("/user_service")
  public ResponseEntity<String> userServiceFallback() {
	return ResponseEntity.ok("User Service şuanda hizmet veremiyor!!!");
  }

  @GetMapping("/company_service")
  public ResponseEntity<String> companyServiceFallback() {
	return ResponseEntity.ok("Company Service şuanda hizmet veremiyor!!!");
  }

  @GetMapping("/payment_service")
  public ResponseEntity<String> paymentServiceFallback() {
	return ResponseEntity.ok("Payment Service şuanda hizmet veremiyor!!!");
  }

  @GetMapping("/comment_service")
  public ResponseEntity<String> commentServiceFallback() {
	return ResponseEntity.ok("Comment Service şuanda hizmet veremiyor!!!");
  }

  @GetMapping("/permission_service")
  public ResponseEntity<String> permissionServiceFallback() {
	return ResponseEntity.ok("Permission Service şuanda hizmet veremiyor!!!");
  }
}
