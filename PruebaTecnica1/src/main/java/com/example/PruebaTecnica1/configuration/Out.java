package com.example.PruebaTecnica1.configuration;

import lombok.Data;

import java.time.Instant;

@Data
public final class Out<T> {
    private T data;
    private String status;
    private Instant timestamp = Instant.now();
    private String message;
    private ErrorOut error;
}
