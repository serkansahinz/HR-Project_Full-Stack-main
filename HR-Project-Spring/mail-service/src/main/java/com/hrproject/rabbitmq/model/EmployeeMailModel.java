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
public class EmployeeMailModel implements Serializable {
  private String personalEmail;
  private String password;
  private String name;
  private String surname;
  private String email;
}
