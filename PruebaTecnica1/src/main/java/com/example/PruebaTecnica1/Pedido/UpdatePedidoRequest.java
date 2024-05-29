package com.example.PruebaTecnica1.Pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePedidoRequest {

    @NotEmpty(message = "el id de pedido es obligatorio")
    private String id;

    @NotBlank(message = "el id de cliente es obligatorio")
    private String clienteId;

    @NotEmpty(message = "La lista de productos es obligatoria")
    private List<String> productosIds;
}
