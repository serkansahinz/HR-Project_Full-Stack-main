package com.hrproject.repository.enums;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum EType {
  BABALIK(10),
  ANNELİK(90),
  GEBELİK(90),
  YILLIK(14),

  ;

  private int kalanGün;
}
