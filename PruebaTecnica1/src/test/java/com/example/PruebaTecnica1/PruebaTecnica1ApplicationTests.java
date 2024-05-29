package com.example.PruebaTecnica1;

import com.example.PruebaTecnica1.Producto.Producto;
import com.example.PruebaTecnica1.Producto.ProductoService;
import com.example.PruebaTecnica1.Producto.ProductoStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaTecnica1ApplicationTests {
    @Mock
    private ProductoStorage productoStorage;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void testFindAll() {
        // Datos de ejemplo
        Producto producto1 = new Producto("producto1", 10.0);
        Producto producto2 = new Producto("producto2", 15.0);
        List<Producto> productos = Arrays.asList(producto1, producto2);

        // Simular el comportamiento del repositorio
        when(productoStorage.findAll()).thenReturn(Flux.fromIterable(productos));

        // Llamar al método del servicio
        Flux<Producto> resultado = productoService.findAll();

        // Verificar que el resultado sea el esperado
        StepVerifier.create(resultado)
                .expectNext(producto1)
                .expectNext(producto2)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        // Datos de ejemplo
        String productoId = "1e767fad-4f36-40c6-9b4f-202110a40512";
        Producto producto = new Producto(productoId, 10.0);

        // Simular el comportamiento del repositorio
        when(productoStorage.findById(UUID.fromString(productoId))).thenReturn(Mono.just(producto));

        // Llamar al método del servicio
        Mono<Producto> resultado = productoService.findById(UUID.fromString(productoId));

        // Verificar que el resultado sea el esperado
        StepVerifier.create(resultado)
                .expectNext(producto)
                .verifyComplete();
    }
}
