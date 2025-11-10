package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

}