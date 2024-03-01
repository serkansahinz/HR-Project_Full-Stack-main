package com.hrproject.dto.response;

import com.hrproject.repository.enums.ECurrency;
import com.hrproject.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePaymentResponseDto {
  private String id;
  private String paymentName;
  private LocalDate paymentDate;
  private double amount;
  private String paymentDetail;
  private String companyId;
  private String companyName;
  private ECurrency currency;
  private String userId;
  private String fullName;
  private EStatus status;
  private LocalDate createdDate;
  private LocalDate updatedDate;
  private String fileUrl;
}
