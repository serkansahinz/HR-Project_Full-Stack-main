package com.hrproject.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrproject.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {
  @JsonIgnore
  private Long id;
  @NotBlank(message = "Email adı boş geçilemez")
  @Email
  private String email;
  @NotBlank(message = "Şifre adı boş geçilemez")
  @Size(min = 5, max = 32, message = "Şifre uzunlugu en az 5 karakter en fazla 32 karakter olabilir.")
  private String password;
  private String name;
  private String surname;
  private String phone;

}
