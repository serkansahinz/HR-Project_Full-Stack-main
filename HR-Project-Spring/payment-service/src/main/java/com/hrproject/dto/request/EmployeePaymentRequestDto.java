package com.hrproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePaymentRequestDto {
  private String paymentName;
  private LocalDate paymentDate;
  private double amount;
  private String paymentDetail;
  private String companyName;
  private String currency;
  private String name;
  private String surname;
}
