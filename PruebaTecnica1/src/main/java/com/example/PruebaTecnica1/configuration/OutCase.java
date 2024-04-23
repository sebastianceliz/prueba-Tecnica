package com.example.PruebaTecnica1.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class OutCase {

  public static <T> ResponseEntity<Out<T>> ok(T data) {
    var response = new Out<T>();
    response.setData(data);
    response.setStatus(HttpStatus.OK.name());
    response.setMessage("OK");
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<Out<T>> ok(String message) {
    var response = new Out<T>();
    response.setStatus(HttpStatus.OK.name());
    response.setMessage(message);
    return ResponseEntity.ok().body(response);
  }

  public static <T> ResponseEntity<Out<T>> ok(T data, String message) {
    var response = new Out<T>();
    response.setData(data);
    response.setStatus(HttpStatus.OK.name());
    response.setMessage(message);
    return ResponseEntity.ok(response);
  }

  public static ResponseEntity<Out<Void>> created(String message) {
    var response = new Out<Void>();
    response.setData(null);
    response.setStatus(HttpStatus.CREATED.name());
    response.setMessage(message);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public static <T> ResponseEntity<Out<T>> created(T data, String message) {
    var response = new Out<T>();
    response.setData(data);
    response.setStatus(HttpStatus.CREATED.name());
    response.setMessage(message);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public static <T> ResponseEntity<Out<T>> error(HttpStatus status, String message) {
    ErrorOut error = new ErrorOut();
    error.setMessage(message);
    var response = new Out<T>();
    response.setStatus(status.name());
    response.setError(error);
    return ResponseEntity.status(status).body(response);
  }

  public static <T> ResponseEntity<Out<T>> error(HttpStatus status, String code, String message) {
    ErrorOut error = new ErrorOut();
    error.setMessage(message);
    error.setCode(code);
    var response = new Out<T>();
    response.setStatus(status.name());
    response.setError(error);
    return ResponseEntity.status(status).body(response);
  }

  public static ResponseEntity<Object> error(
      ErrorOut error, HttpHeaders headers, HttpStatus status) {
    var response = new Out<>();
    response.setStatus(status.name());
    response.setError(error);
    return ResponseEntity.status(status).headers(headers).body(response);
  }
}
