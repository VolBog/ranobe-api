package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.NovelDto;
import java.util.Collections;
import java.util.List;

public class NovelBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static NovelDto getDto() {
    NovelDto dto = new NovelDto();
    dto.setId("1");
    return dto;
  }
}
