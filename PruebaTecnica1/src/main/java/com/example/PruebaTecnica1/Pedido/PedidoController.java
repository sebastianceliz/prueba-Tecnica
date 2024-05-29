package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.configuration.Out;
import com.example.PruebaTecnica1.configuration.OutCase;
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

    /*
    EJEMPLO URL GET: http://localhost:8080/api/pedidos
     */

    @GetMapping
    public Flux<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }


    /*
    EJEMPLO URL GET: http://localhost:8080/api/pedidos/6657806701cc0e5840966a16
     */
    @GetMapping("/{id}")
    public Mono<Pedido> getPedidoById(@PathVariable String id) {
        return pedidoService.getPedidoById(id);
    }



    /*URL POST: http://localhost:8080/api/pedidos

    ejemplo body para create:



     */


    @PostMapping
    public Mono<ResponseEntity<Out<String>>> createPedido(@Valid @RequestBody CreatePedidoRequest createPedidoRequest) {
        return pedidoService.createPedido(createPedidoRequest.getClienteId(), createPedidoRequest.getProductosIds())
                .then(OutCase.ok("Pedido creado correctamente", "OK"));
    }


    /*URL PUT: http://localhost:8080/api/pedidos/update

    ejemplo body para update:

    {
  "id": "6657806701cc0e5840966a16",
  "clienteId": "febf4209-324f-4986-a0f6-fd1008607137",
  "productosIds": ["1e767fad-4f36-40c6-9b4f-202110a40511","2aeb3bb8-764c-437e-bbe2-b33a3255b16d"]
    }

 */
    @PutMapping("/update")
    public Mono<ResponseEntity<Out<String>>> updatePedido(@Valid @RequestBody UpdatePedidoRequest updatePedidoRequest) {
        return pedidoService.updatePedido(updatePedidoRequest.getId(), updatePedidoRequest.getClienteId(), updatePedidoRequest.getProductosIds())
                .then(OutCase.ok(updatePedidoRequest, "Pedido " + updatePedidoRequest.getId() + " ha sido actualizado correctamente."));
    }


    //ejemplo : http://localhost:8080/api/pedidos/66578b6628698e3c2afbb79e
    @DeleteMapping("/{pedidoId}")
    public Mono<ResponseEntity<String>> deletePedido(@Valid @PathVariable String pedidoId) {

        return pedidoService.deletePedido(pedidoId)
                .then(Mono.just(ResponseEntity.ok("Pedido " + pedidoId + " ha sido eliminado correctamente.")));
    }
}
