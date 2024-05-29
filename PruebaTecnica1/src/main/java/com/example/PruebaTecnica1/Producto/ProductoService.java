package com.example.PruebaTecnica1.Producto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoStorage productoStorage;

    public Flux<Producto> findAll() {
        return productoStorage.findAll();
    }

    public Mono<Producto> findById(UUID id) {
        return productoStorage.findById(id);
    }

    public Mono<Producto> save(Producto producto) {
        return productoStorage.save(producto);
    }

    public Mono<Void> deleteById(UUID id) {
        return productoStorage.deleteById(id);
    }

    public Flux<Producto> getProductosByIds(List<String> ids) {
        List<UUID> uuids = ids.stream().map(UUID::fromString).collect(Collectors.toList());
        return productoStorage.findAllById(uuids);
    }
}
