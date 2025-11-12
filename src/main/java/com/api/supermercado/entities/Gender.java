package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @Column(name = "gender_id", nullable = false)
    private Integer genderId;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

}