package com.example.PruebaTecnica1.Cliente;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ClienteStorage extends R2dbcRepository<Cliente, UUID> {
}
