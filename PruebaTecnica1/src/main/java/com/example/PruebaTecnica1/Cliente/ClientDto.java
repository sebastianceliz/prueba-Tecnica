package com.example.PruebaTecnica1.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientDto {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate fechaNacimiento;

}
