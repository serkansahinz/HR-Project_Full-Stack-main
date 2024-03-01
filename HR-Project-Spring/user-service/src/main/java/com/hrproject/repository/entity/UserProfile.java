package com.hrproject.repository.entity;

import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EGender;
import com.hrproject.repository.enums.EShift;
import com.hrproject.repository.enums.EStatus;
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
public class UserProfile extends BaseEntity {
  @Id
  private String id;
  private Long authId;
  private String email;
  private String phone;
  private String avatar;
  private String name;
  private String surname;
  private String province;
  private String street;
  private LocalDate birthDate;
  private String companyId;
  private String companyName;
  private String salary;
  private String department;
  private EShift shift;
  private EGender gender;
  private ERole role;
  @Builder.Default
  private EStatus status = EStatus.PENDING;
}
