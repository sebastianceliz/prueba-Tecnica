package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.Producto.Producto;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pedido")
@Data
public class Pedido {
    @Id
    private String id;
    @NotNull
    private String clienteId;
    @NotEmpty
    private List<Producto> productos;
    private double total;
}
