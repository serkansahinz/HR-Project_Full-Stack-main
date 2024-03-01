package com.hrproject.controller;

import com.hrproject.dto.request.EmployeePaymentRequestDto;
import com.hrproject.dto.request.PaymentRequestDto;
import com.hrproject.dto.response.EmployeePaymentResponseDto;
import com.hrproject.dto.response.PaymentInfoResponseDto;
import com.hrproject.dto.response.PaymentResponseDto;
import com.hrproject.service.PaymentService;
import com.hrproject.utility.CurrencyInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(PAYMENT)
@RequiredArgsConstructor
@CrossOrigin

public class PaymentController {
  private final PaymentService paymentService;

  @PostMapping(CREATE_NEW_PAYMENT + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<PaymentResponseDto> createNewPayment(@PathVariable String companyId,@RequestBody PaymentRequestDto dto) {
	return ResponseEntity.ok(paymentService.createNewPayment(companyId,dto));
  }

  @GetMapping(GET_ALL_INCOME + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<List<PaymentResponseDto>> getAllIncome(@PathVariable String companyId) {
	return ResponseEntity.ok(paymentService.getAllIncome(companyId));
  }

  @GetMapping(GET_ALL_EXPENSE + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<List<PaymentResponseDto>> getAllExpense(@PathVariable String companyId) {
	return ResponseEntity.ok(paymentService.getAllExpense(companyId));
  }

  @DeleteMapping(DELETE_BY_ID + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
	paymentService.deleteById(id);
	return ResponseEntity.ok(true);
  }

  @GetMapping("get_calculation/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<PaymentInfoResponseDto> getCalculation(@PathVariable String companyId) {
	return ResponseEntity.ok(paymentService.calculateTotalIncomeAndExpense(companyId));
  }

  @PostMapping(EMPLOYEE_CREATE_NEW_PAYMENT + "/{companyId}/{userId}")
  @PreAuthorize("hasAuthority('EMPLOYEE')")
  public ResponseEntity<EmployeePaymentResponseDto> employeeCreatePayment(@PathVariable String companyId,@PathVariable String userId,@RequestBody EmployeePaymentRequestDto dto) {
	return ResponseEntity.ok(paymentService.employeeCreatePayment(companyId,userId,dto));
  }

  @GetMapping(GET_ALL_PENDING_PAYMENT + "/{companyId}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<List<EmployeePaymentResponseDto>> getAllPendingPayment(@PathVariable String companyId) {
	return ResponseEntity.ok(paymentService.getAllPendingPayment(companyId));
  }

  @PutMapping(ACTIVATE_STATUS + "/{id}")
  @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<EmployeePaymentResponseDto> activateStatus(@PathVariable String id) {
	return ResponseEntity.ok(paymentService.activateStatus(id));
  }

  @GetMapping(GET_ALL_PAYMENTS_BY_USER_ID + "/{userId}")
  public ResponseEntity<List<EmployeePaymentResponseDto>> getAllEmployeePaymentsById(@PathVariable String userId) {
	return ResponseEntity.ok(paymentService.getAllPaymentsByUserId(userId));
  }

  @PutMapping(DELETE_EMPLOYEE_PAYMENT + "/{id}")
  public ResponseEntity<Boolean> deleteEmployeePayment(@PathVariable String id) {
	return ResponseEntity.ok(paymentService.deleteEmployeePayment(id));
  }

  //List<TCMBAnlikKurBilgisi> getCurrency()

  @GetMapping("/get_currency")
  public ResponseEntity<List<CurrencyInfo>> getCurrency() {
	return ResponseEntity.ok(paymentService.getCurrency());
  }

  @PutMapping(UPLOAD_FILE + "/{id}")
  public ResponseEntity<EmployeePaymentResponseDto> uploadFile(@PathVariable String id,@RequestParam MultipartFile file) throws IOException {
	return ResponseEntity.ok(paymentService.uploadFile(id,file));
  }
}
