package com.example.PruebaTecnica1.Cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteStorage clientStorage;
    private final ClientMapper clientMapper;

    public void createClient(ClientDto clientDto) {
        Cliente client = clientMapper.toEntity(clientDto);
        clientStorage.save(client);
    }

    public List<Cliente> getAllClientes() {
        //llamar a todos los clientes con JPA
        return clientStorage.findAll();
    }

    public double calcularPromedioEdad(List<Cliente> clientes) {
        //uso de stream para acceder a los atributos de lista de clientes
        int totalEdades = clientes.stream()
                .mapToInt(Cliente::getEdad)
                .sum();
        return (double) totalEdades / clientes.size();
    }

    public double calcularDesviacionEstandar(List<Cliente> clientes, double promedioEdad) {
        double sumaDiferenciaCuadrado = clientes.stream()
                .mapToDouble(cliente -> Math.pow(cliente.getEdad() - promedioEdad, 2))
                .sum();
        return Math.sqrt(sumaDiferenciaCuadrado / clientes.size());
    }
}
