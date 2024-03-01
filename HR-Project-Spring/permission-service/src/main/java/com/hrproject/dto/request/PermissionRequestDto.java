package com.hrproject.dto.request;

import com.hrproject.repository.enums.EType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequestDto {
  private String type;
  private LocalDate startDate;
  private LocalDate endDate;
  private String userId;
  private String name;
  private String surname;
  private String companyId;
  private String gender;
}
