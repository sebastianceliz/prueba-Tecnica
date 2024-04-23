package com.example.PruebaTecnica1.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Table(name = "client")
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;

    private String apellido;

    private int edad;

    private LocalDate fechaNacimiento;
    //ejemplo : 2024-04-22
}
