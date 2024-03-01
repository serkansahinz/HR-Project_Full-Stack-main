package com.hrproject.repository.entity;

import com.hrproject.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Comment extends BaseEntity {
  @Id
  private String id;
  private String userId;
  private String fullName;
  private String companyId;
  private String companyName;
  private String header;
  private String content;

  @Builder.Default
  private EStatus status = EStatus.PENDING;
}
