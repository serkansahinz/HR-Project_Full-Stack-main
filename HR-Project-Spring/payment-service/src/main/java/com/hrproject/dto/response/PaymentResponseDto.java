package com.hrproject.dto.response;

import com.hrproject.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {
  private String id;
  private String paymentName;
  private LocalDate paymentDate;
  private double amount;
  private String paymentDetail;
  private String companyId;
  private String companyName;
  private ECurrency currency;
  private String fileUrl;
}
