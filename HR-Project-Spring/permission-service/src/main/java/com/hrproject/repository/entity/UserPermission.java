package com.hrproject.repository.entity;

import com.hrproject.repository.enums.EType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.EnumMap;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class UserPermission extends BaseEntity {
  @Id
  private String id;
  private String userId;
  private String fullName;

  @Builder.Default
  private Map<EType, Integer> remainingPermissions = new EnumMap<>(EType.class);
}
