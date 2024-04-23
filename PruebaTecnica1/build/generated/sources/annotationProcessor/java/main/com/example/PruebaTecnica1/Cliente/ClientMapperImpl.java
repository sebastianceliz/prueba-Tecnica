package com.example.PruebaTecnica1.Cliente;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T21:45:11-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Cliente toEntity(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre( clientDto.getNombre() );
        cliente.setApellido( clientDto.getApellido() );
        if ( clientDto.getEdad() != null ) {
            cliente.setEdad( clientDto.getEdad() );
        }
        cliente.setFechaNacimiento( clientDto.getFechaNacimiento() );

        return cliente;
    }
}
