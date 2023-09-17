package com.volbog.ranobe.configuration.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DTO object to read cors properties from application yml
 */
@Data
@ConfigurationProperties(prefix = "app.web")
public class WebConfigDto {

  private Cors cors;

  @Data
  public static class Cors {

    String[] allowedOrigins;
    String[] allowedHeaders;
    String[] allowedMethods;
  }
}
