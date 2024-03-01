package com.hrproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequestDto {
  @NotBlank(message = "Email adı boş geçilemez.")
  private String email;
  @NotBlank(message = "Şifre adı boş geçilemez.")
  private String password;
}
