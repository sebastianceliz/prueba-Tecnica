package com.example.PruebaTecnica1.Producto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductoService productoService;

    @GetMapping("/getAllProducts")
    public Flux<Producto> findAll() {
        return productoService.findAll();
    }
}
