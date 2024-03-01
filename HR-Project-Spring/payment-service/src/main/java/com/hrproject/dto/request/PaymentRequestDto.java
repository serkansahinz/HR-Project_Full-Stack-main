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
public class PaymentRequestDto {

  private String paymentName;
  private LocalDate paymentDate;
  private double amount;
  private String paymentDetail;
  private String companyName;
  private String paymentType;
  private String currency;

}
