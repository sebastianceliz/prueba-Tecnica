package com.example.PruebaTecnica1.Cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Component
public class ClienteService {

    private final ClienteStorage clienteRepository;

    public Flux<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Mono<Cliente> findById(UUID id) {
        return clienteRepository.findById(id);
    }

    public Mono<Cliente> save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Mono<Void> deleteById(UUID id) {
        return clienteRepository.deleteById(id);
    }
}
