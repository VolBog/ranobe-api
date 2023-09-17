package com.volbog.ranobe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException() {
    super("Not Found!!!");
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
