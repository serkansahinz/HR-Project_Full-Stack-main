package com.hrproject.rabbitmq.model;

import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeAuthModel implements Serializable {
  private Long authId;
  private ERole role;
  private String email;
  private EStatus status;
  private String phone;
  private String name;
  private String surname;
  private String companyId;
  private String companyName;
  private String province;
  private String street;
  private LocalDate birthDate;
  private String salary;
  private String department;
  private String shift;
  private String gender;
}
