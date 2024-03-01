package com.hrproject.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterModel implements Serializable {
  private Long authId;
  private String name;
  private String surname;
  private String phone;
  private String email;
  private String province;
  private String street;
  private String companyName;
  private String role;
  private String gender;
}
