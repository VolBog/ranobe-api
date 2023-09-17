package com.volbog.ranobe.dto;

import com.volbog.ranobe.entity.Chapter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
public class TranslationDto extends AbstractDto<Long> {

  private Long id;
  private Chapter chapter;
  private String originalText;
  private String translatedText;

}