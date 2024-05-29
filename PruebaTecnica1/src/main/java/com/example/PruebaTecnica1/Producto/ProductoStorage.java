package com.example.PruebaTecnica1.Producto;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoStorage extends R2dbcRepository<Producto, UUID> {
}
