package com.hrproject.dto.response;

import com.hrproject.repository.enums.EGender;
import com.hrproject.repository.enums.EShift;
import com.hrproject.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDto implements Serializable {
  private String id;
  private Long authId;
  private String companyId;
  private String companyName;
  private String email;
  private String phone;
  private String avatar;
  private String about;
  private String name;
  private String surname;
  private String department;
  private String province;
  private String street;
  private LocalDate birthDate;
  private EStatus status;
  private String salary;
  private EGender gender;
  private EShift shift;
}
