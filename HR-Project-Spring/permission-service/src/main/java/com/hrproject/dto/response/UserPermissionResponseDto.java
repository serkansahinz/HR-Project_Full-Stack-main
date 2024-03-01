package com.hrproject.dto.response;

import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPermissionResponseDto {

  private EType type;
  private int remainingDays;
}
