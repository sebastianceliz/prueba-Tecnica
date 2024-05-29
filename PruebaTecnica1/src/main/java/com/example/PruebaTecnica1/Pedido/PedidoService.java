package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.Cliente.Cliente;
import com.example.PruebaTecnica1.Cliente.ClienteService;
import com.example.PruebaTecnica1.Producto.Producto;
import com.example.PruebaTecnica1.Producto.ProductoStorage;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoStorage pedidoStorage;
    private final ClienteService clienteService;

    private final ProductoStorage productoStorage;


    public Flux<Pedido> getAllPedidos() {
        return pedidoStorage.findAll();
    }

    public Mono<Pedido> getPedidoById(String id) {
        return pedidoStorage.findById(id);
    }

    @Transactional
    public Mono<Pedido> createPedido(String clienteId, List<String> productosIds) {

        List<UUID> uuidProductosIds = convertirAUUID(productosIds);

        // Verificar si el cliente existe
        Mono<Cliente> clienteMono = clienteService.findById(UUID.fromString(clienteId))
                .switchIfEmpty(Mono.error(new NotFoundException("Cliente not found with ID: " + clienteId)));

        Flux<Producto> productosFlux = obtenerProductos(uuidProductosIds);

        // Verificar si los productos existen
        Flux<Producto> productosFlux1 = obtenerProductos(uuidProductosIds)
                .switchIfEmpty(Mono.error(new NotFoundException("One or more products not found.")));


        Mono<Double> totalMono = calcularTotal(productosFlux);

        return totalMono.flatMap(total -> {
            return crearPedido(clienteId, productosFlux.collectList(), total);
        }).flatMap(pedidoStorage::save);
    }

    private List<UUID> convertirAUUID(List<String> productosIds) {
        return productosIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
    }

    private Flux<Producto> obtenerProductos(List<UUID> uuidProductosIds) {
        return productoStorage.findAllById(uuidProductosIds);
    }

    private Mono<Double> calcularTotal(Flux<Producto> productosFlux) {
        return productosFlux.map(Producto::getPrecio)
                .reduce(0.0, Double::sum);
    }

    private Mono<Pedido> crearPedido(String clienteId, Mono<List<Producto>> productosMono, double total) {

        return productosMono.map(productos -> {
            Pedido pedido = new Pedido();
            pedido.setClienteId(clienteId);
            pedido.setProductos(productos);
            pedido.setTotal(total);
            return pedido;
        });
    }

    @Transactional
    public Mono<Pedido> updatePedido(String pedidoId, String clienteId, List<String> productosIds) {
        List<UUID> uuidProductosIds = convertirAUUID(productosIds);

        Flux<Producto> productosFlux = obtenerProductos(uuidProductosIds);

        Mono<Double> totalMono = calcularTotal(productosFlux);

        return pedidoStorage.findById(pedidoId)
                .flatMap(existingPedido -> totalMono.flatMap(total -> {
                    return crearPedido(clienteId, productosFlux.collectList(), total)
                            .flatMap(nuevoPedido -> {
                                existingPedido.setClienteId(nuevoPedido.getClienteId());
                                existingPedido.setProductos(nuevoPedido.getProductos());
                                existingPedido.setTotal(nuevoPedido.getTotal());
                                return pedidoStorage.save(existingPedido);
                            });
                }));
    }

    public Mono<Void> deletePedido(String pedidoId) {
        return pedidoStorage.deleteById(pedidoId);
    }
}
