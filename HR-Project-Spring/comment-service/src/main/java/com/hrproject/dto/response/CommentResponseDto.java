package com.hrproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
  private String id;
  private String userId;
  private String companyId;
  private String companyName;
  private String header;
  private String content;
  private String fullName;
}
