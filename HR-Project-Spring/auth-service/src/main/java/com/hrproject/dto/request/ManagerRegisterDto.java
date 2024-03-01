package com.hrproject.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrproject.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ManagerRegisterDto {
  //User service e gidecek
  @JsonIgnore
  private Long id;
  @NotBlank(message = "İsim  boş geçilemez.")
  private String name;
  @NotBlank(message = "Soyisim boş geçilemez.")
  private String surname;
  private String phone;
  @NotBlank(message = "Cinsiyet boş geçilemez.")
  private String gender;
  //Auth service e gidecek
  @NotBlank(message = "Email boş geçilemez.")
  @Email
  private String email;
  @NotBlank(message = "Şifre boş geçilemez.")
  @Size(min = 5, max = 32, message = "Şifre uzunlugu en az 5 karakter en fazla 32 karakter olabilir.")
  private String password;

  //TODO bundan sonrası companye gönderilecek
  @NotBlank(message = "Şirket adı boş geçilemez.")
  private String companyName;
  @NotBlank(message = "Şirket vergi numarası boş geçilemez.")
  private String companyTaxNumber;
  @NotBlank(message = "Şirket maili boş geçilemez.")
  private String companyEmail;
  private String companyPhone;
  // @NotBlank(message = "Şirket ili boş geçilemez.")
  private String province;
  //  @NotBlank(message = "Şirket caddesi boş geçilemez.")
  private String street;
  private int remainingDays;
}
