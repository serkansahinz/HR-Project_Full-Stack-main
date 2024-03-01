package com.hrproject.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRegisterModel implements Serializable {
  private Long companyManagerId;
  private String companyManagerMail;
  private String companyName;
  private String companyTaxNumber;
  private String companyEmail;
  private String companyPhone;
  private String province;
  private String street;
  private String token;
  private int remainingDays;
}
