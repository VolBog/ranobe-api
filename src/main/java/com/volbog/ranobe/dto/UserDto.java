package com.volbog.ranobe.dto;

import com.volbog.ranobe.annotation.CheckEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
public class UserDto extends AbstractDto<Long> {

  private Long id;
  @Size(max = 255)
  @NotBlank
  private String name;
  @CheckEmail
  @Size(max = 255)
  @NotBlank
  private String email;
  @Size(max = 255)
  @NotBlank
  private String password;
}