package com.hrproject.dto.response;

import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponseDto implements Serializable {
  private String id;
  private EType type;
  private LocalDate startDate;
  private LocalDate endDate;
  private String userId;
  private String fullName;
  private String companyId;
  private EStatus status;
  private LocalDate updatedDate;
  private LocalDate createdDate;
}
