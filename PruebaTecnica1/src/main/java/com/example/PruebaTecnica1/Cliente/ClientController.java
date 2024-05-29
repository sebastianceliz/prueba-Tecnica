package com.example.PruebaTecnica1.Cliente;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClienteService clienteService;

    @GetMapping("/client/{id}")
    public Mono<Cliente> findById(@PathVariable UUID id) {
        return clienteService.findById(id);
    }

    @GetMapping("/getAllClients")
    public Flux<Cliente> findAll() {
        return clienteService.findAll();
    }

    @PostMapping
    public Mono<Cliente> save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Mono<Cliente> update(@PathVariable UUID id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable UUID id) {
        return clienteService.deleteById(id);
    }
}
