package com.hrproject.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendCompanyIdToManagerModel implements Serializable {
  private String companyId;
  private Long companyManagerId;
}
