package com.example.PruebaTecnica1.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        ErrorOut error = new ErrorOut();
        error.setMessage("Field validation has failed.");
        error.addValidationFieldErrors(ex.getBindingResult().getFieldErrors());
        error.addValidationGlobalErrors((ex.getBindingResult().getGlobalErrors()));
        return OutCase.error(
                error, headers, Objects.requireNonNull(HttpStatus.resolve(status.value())));
    }
}
