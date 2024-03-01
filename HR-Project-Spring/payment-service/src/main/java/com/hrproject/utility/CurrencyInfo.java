package com.hrproject.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyInfo {
  private String Isim;
  private String CurrencyName;
  private String ForexBuying;
  private String ForexSelling;
  private String BanknoteBuying;
  private String BanknoteSelling;
  private String CrossRateUSD;
  private String CrossRateOther;
}
