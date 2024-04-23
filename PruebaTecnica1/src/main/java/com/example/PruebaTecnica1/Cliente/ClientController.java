package com.example.PruebaTecnica1.Cliente;

import com.example.PruebaTecnica1.configuration.Out;
import com.example.PruebaTecnica1.configuration.OutCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClienteService clientService;


    @PostMapping("/creacliente")
    public ResponseEntity<Out<Void>> createClient(@Valid @RequestBody ClientDto clientDto) {
        //uso de mapstruct para convertir el DTO con las validaciones a la entidad
        clientService.createClient(clientDto);
        //formato de respuesta "200 ok"
        return OutCase.ok("Cliente creado satisfactoriamente");
    }
    //URL : http://127.0.0.1:8080/creacliente


    @GetMapping("/kpideclientes")
    public ResponseEntity<Out<Map<String, Double>>> getKpiClientes() {
        List<Cliente> clientes = clientService.getAllClientes();

        // invocando al metodo para calcular edad promedio
        double promedioEdad = clientService.calcularPromedioEdad(clientes);

        // invocando al metodo para calcular la desviacion estandar
        double desviacionEstandar = clientService.calcularDesviacionEstandar(clientes, promedioEdad);

        // Crear un mapa para almacenar los resultados
        Map<String, Double> resultados = new HashMap<>();
        resultados.put("promedioEdad", promedioEdad);
        resultados.put("desviacionEstandar", desviacionEstandar);

        return OutCase.ok(resultados);
    }

    //URL : http://127.0.0.1:8080/kpideclientes

    @GetMapping("/listclientes")
    public ResponseEntity<Out<List<Cliente>>> getClients() {
        //crear una lista e invocar a los clientes
        List<Cliente> clients = clientService.getAllClientes();
        return OutCase.ok(clients);
    }

    // URL : http://127.0.0.1:8080/listclientes
}
