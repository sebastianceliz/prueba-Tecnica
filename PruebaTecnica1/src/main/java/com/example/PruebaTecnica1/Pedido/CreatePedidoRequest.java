package com.example.PruebaTecnica1.Pedido;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreatePedidoRequest {
    @NotNull(message = "el cliente no puede ser nulo")
    private String clienteId;
    @NotNull(message = "La lista de productos es obligatoria")
    private List<String> productosIds;
}
