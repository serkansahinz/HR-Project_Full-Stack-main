package com.hrproject.dto.response;

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
public class AdvanceResponseDto {
  private String userId;
  private String companyId;
  private double amount;
  private String fullName;
  private LocalDate createdDate;
  private LocalDate updatedDate;
  private EStatus status;
  private String id;
}
