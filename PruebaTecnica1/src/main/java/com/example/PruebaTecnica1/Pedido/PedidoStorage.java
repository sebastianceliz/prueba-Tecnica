package com.example.PruebaTecnica1.Pedido;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoStorage extends ReactiveMongoRepository<Pedido,String> {
}
