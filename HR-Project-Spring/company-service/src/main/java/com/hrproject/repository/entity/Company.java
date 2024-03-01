package com.hrproject.repository.entity;

import com.hrproject.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Company extends BaseEntity {
  @Id
  private String id;
  private Long companyManagerId;
  private String companyManagerMail;
  private String companyName;
  private String companyEmail;
  private String companyPhone;
  private String companyTaxNumber;
  private String province;
  private String street;
  private String token;
  private int remainingDays;
  @Builder.Default
  private EStatus status = EStatus.PENDING;
}
