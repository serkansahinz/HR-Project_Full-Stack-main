package com.hrproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDto {
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
}
