package com.hrproject.dto.request;

import com.hrproject.repository.enums.EShift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerUpdateEmployeeRequestDto {
  private String salary;
  private String department;
  private String shift;
}
