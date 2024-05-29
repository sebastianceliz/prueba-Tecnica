package com.example.PruebaTecnica1.Pedido;

import com.example.PruebaTecnica1.Producto.Producto;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pedido")
@Data
public class Pedido {
    @Id
    private String id;
    private String clienteId;
    private List<Producto> productos;
    private double total;
}
