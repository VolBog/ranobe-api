package com.volbog.ranobe.dto;

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
public class RoleDto extends AbstractDto<Long> {

  private Long id;
  @Size(max = 255)
  @NotBlank
  private String name;
}