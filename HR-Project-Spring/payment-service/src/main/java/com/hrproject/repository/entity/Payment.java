package com.hrproject.repository.entity;

import com.hrproject.repository.enums.ECurrency;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Payment extends BaseEntity {
  @Id
  private String id;
  private String paymentName;
  private LocalDate paymentDate;
  private double amount;
  private String paymentDetail;
  private String companyId;
  private String companyName;
  private EType paymentType;
  private ECurrency currency;
  private String userId;
  private String fullName;
  private EStatus status;
  private String fileUrl;
}
