package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.TranslationDto;
import java.util.Collections;
import java.util.List;

public class TranslationBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static TranslationDto getDto() {
    TranslationDto dto = new TranslationDto();
    dto.setId("1");
    return dto;
  }
}
