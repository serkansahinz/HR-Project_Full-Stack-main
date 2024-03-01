package com.hrproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequestDto {

  private String phone;
  @NotBlank(message = "İsim boş geçilemez.")
  private String name;
  @NotBlank(message = "Soyisim boş geçilemez.")
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
  private String personalEmail;
}
