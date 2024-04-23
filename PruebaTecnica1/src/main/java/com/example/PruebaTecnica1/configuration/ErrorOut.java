package com.example.PruebaTecnica1.configuration;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
public final class ErrorOut {

  private String message;
  private String code;
  private List<ValidationErrorOut> errors;

  private void addError(ValidationErrorOut error) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }

  private void addValidationError(String object, String field, String message) {
    addError(new ValidationErrorOut(object, field, message));
  }

  private void addValidationError(String object, String message) {
    addError(new ValidationErrorOut(object, message));
  }

  private void addValidationError(FieldError fieldError) {
    this.addValidationError(
        fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
  }

  public void addValidationError(ObjectError objectError) {
    this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
  }

  public void addValidationFieldErrors(List<FieldError> fieldErrors) {
    fieldErrors.forEach(this::addValidationError);
  }

  public void addValidationGlobalErrors(List<ObjectError> objectErrors) {
    objectErrors.forEach(this::addValidationError);
  }
}
