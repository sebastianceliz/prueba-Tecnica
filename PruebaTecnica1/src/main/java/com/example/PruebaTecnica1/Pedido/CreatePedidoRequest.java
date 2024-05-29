package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.Producto.Producto;
import lombok.Data;

import java.util.List;

@Data
public class CreatePedidoRequest {
    private String clienteId;
    private List<Producto> productos;

    public CreatePedidoRequest() {
        // Constructor por defecto
    }
}
