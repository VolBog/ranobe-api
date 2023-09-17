package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.GlossaryTermDto;
import java.util.Collections;
import java.util.List;

public class GlossaryTermBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static GlossaryTermDto getDto() {
    GlossaryTermDto dto = new GlossaryTermDto();
    dto.setId("1");
    return dto;
  }
}
