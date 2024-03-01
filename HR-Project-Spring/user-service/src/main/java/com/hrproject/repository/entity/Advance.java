package com.hrproject.repository.entity;

import com.hrproject.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Advance extends BaseEntity {
  @Id
  private String id;
  private double amount;
  private String companyId;
  private String userId;
  private String fullName;
  @Builder.Default
  private EStatus status=EStatus.PENDING;

}
