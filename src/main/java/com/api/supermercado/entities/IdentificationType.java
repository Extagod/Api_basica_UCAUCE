package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "identification_type")
public class IdentificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identification_type_id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

}