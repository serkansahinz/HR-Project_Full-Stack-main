package com.hrproject.repository.entity;

import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Permission extends BaseEntity {
  @Id
  private String id;
  private EType type;
  private LocalDate startDate;
  private LocalDate endDate;
  private String userId;
  private String companyId;
  private String fullName;
  private String gender;
  @Builder.Default
  private EStatus status = EStatus.PENDING;
}
