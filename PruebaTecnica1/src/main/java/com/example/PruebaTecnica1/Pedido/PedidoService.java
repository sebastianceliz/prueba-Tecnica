package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.Producto.Producto;
import com.example.PruebaTecnica1.Producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    private final PedidoStorage pedidoStorage;
    private final ProductoService productoService;

    @Autowired
    public PedidoService(PedidoStorage pedidoStorage, ProductoService productoService) {
        this.pedidoStorage = pedidoStorage;
        this.productoService = productoService;
    }

    public Flux<Pedido> getAllPedidos() {
        return pedidoStorage.findAll();
    }

    public Mono<Pedido> getPedidoById(String id) {
        return pedidoStorage.findById(id);
    }

    public Mono<Pedido> createPedido(CreatePedidoRequest pedidoRequest) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(pedidoRequest.getClienteId());
        pedido.setProductos(pedidoRequest.getProductos());
        // Calcular el total sumando los precios de los productos
        double total = pedidoRequest.getProductos().stream().mapToDouble(Producto::getPrecio).sum();
        pedido.setTotal(total);
        // Guardar el pedido en la base de datos
        return pedidoStorage.save(pedido);
    }

}
