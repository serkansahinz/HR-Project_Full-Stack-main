package com.hrproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvanceRequestDto {
  private String userId;
  private String companyId;
  private double amount;
  private String name;
  private String surname;

}
