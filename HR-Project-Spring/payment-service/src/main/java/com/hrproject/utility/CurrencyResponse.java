package com.hrproject.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyResponse {
  private List<CurrencyInfo> TCMB_AnlikKurBilgileri;



}