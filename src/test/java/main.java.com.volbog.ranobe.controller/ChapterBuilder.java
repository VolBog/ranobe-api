package main.java.com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.ChapterDto;
import java.util.Collections;
import java.util.List;

public class ChapterBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static ChapterDto getDto() {
    ChapterDto dto = new ChapterDto();
    dto.setId("1");
    return dto;
  }
}
