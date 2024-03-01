package com.hrproject.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrproject.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileFindAllResponseDto {
 @JsonIgnore
    private String id;
   // private Long userProfileId;
    private Long authId;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private EStatus status;
}
