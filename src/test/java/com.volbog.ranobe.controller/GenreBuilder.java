package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.GenreDto;
import java.util.Collections;
import java.util.List;

public class GenreBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static GenreDto getDto() {
    GenreDto dto = new GenreDto();
    dto.setId("1");
    return dto;
  }
}
