package com.example.PruebaTecnica1.Pedido;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Flux<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Mono<Pedido> getPedidoById(@PathVariable String id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping
    public Mono<Pedido> createPedido(@Valid @RequestBody CreatePedidoRequest createPedidoRequest) {
        return pedidoService.createPedido(createPedidoRequest.getClienteId(), createPedidoRequest.getProductosIds());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> updatePedido(@Valid @RequestBody UpdatePedidoRequest updatePedidoRequest) {
        return pedidoService.updatePedido(updatePedidoRequest.getId(),
                updatePedidoRequest.getClienteId(), updatePedidoRequest.getProductosIds()).then(
                Mono.just(ResponseEntity.ok("Pedido " + updatePedidoRequest.getId()
                        + " ha sido actualizado correctamente.")));
    }

    @DeleteMapping("/{pedidoId}")
    public Mono<ResponseEntity<String>> deletePedido(@Valid @PathVariable String pedidoId) {
        return pedidoService.deletePedido(pedidoId)
                .then(Mono.just(ResponseEntity.ok("Pedido " + pedidoId + " ha sido eliminado correctamente.")));
    }
}
