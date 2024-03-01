package com.hrproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfoResponseDto {
  private double totalIncome;
  private double totalExpense;
  private double netValue;
}
