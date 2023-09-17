package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.RoleDto;
import java.util.Collections;
import java.util.List;

public class RoleBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static RoleDto getDto() {
    RoleDto dto = new RoleDto();
    dto.setId("1");
    return dto;
  }
}
