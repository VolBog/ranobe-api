package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.TagDto;
import java.util.Collections;
import java.util.List;

public class TagBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static TagDto getDto() {
    TagDto dto = new TagDto();
    dto.setId("1");
    return dto;
  }
}
