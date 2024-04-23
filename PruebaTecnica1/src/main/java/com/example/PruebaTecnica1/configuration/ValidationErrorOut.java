package com.example.PruebaTecnica1.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class ValidationErrorOut {
  private String object;
  private String field;
  private String message;

  public ValidationErrorOut(String object, String message) {
    this.object = object;
    this.message = message;
  }
}
