package com.volbog.ranobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The main class for the Ranobe Application.
 */
@EnableWebMvc
@SpringBootApplication
public class RanobeApplication {


  public static void main(String[] args) {
    SpringApplication.run(RanobeApplication.class, args);
  }

}
