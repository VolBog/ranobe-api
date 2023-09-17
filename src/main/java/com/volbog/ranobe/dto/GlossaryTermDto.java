package com.volbog.ranobe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
public class GlossaryTermDto extends AbstractDto<Long> {

  private Long id;
  private String originalTerm;
  private String translatedTerm;
}