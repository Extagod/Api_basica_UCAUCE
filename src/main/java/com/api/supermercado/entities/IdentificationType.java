package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "identification_type")
public class IdentificationType {
    @Id
    @Column(name = "identification_type_id", nullable = false)
    private Integer idIdentificationType;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

}