package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

}