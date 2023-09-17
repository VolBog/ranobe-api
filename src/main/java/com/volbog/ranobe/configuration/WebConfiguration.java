package com.volbog.ranobe.configuration;

import com.volbog.ranobe.configuration.dto.WebConfigDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({WebConfigDto.class})
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

  private final WebConfigDto webConfig;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns(webConfig.getCors().getAllowedOrigins())
        .allowedHeaders(webConfig.getCors().getAllowedHeaders())
        .allowedMethods(webConfig.getCors().getAllowedMethods())
        .allowCredentials(true);
  }
}
