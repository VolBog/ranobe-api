package com.volbog.ranobe.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDto {

  @NotNull
  @Size(max = 255)
  private String email;

  @NotNull
  @Size(max = 255)
  private String password;
}
