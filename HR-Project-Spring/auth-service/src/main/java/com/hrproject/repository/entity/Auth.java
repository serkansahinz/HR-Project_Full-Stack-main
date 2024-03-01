package com.hrproject.repository.entity;

import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Auth extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String password;
  private String email;
  private String activationCode;
  @Enumerated(EnumType.STRING)
  private ERole role;
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private EStatus status = EStatus.PENDING;
}
