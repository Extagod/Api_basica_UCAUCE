package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "branch")
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id", nullable = false)
    private Integer idBranch;

    @NotBlank(message = "Branch name cannot be empty")
    @Size(min = 3, max = 100, message = "Branch name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String nameBranch;

    @NotBlank(message = "Branch address cannot be empty")
    @Size(min = 5, max = 200, message = "Branch address must be between 5 and 200 characters")
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Pattern(
            regexp = "^$|^[0-9]{7,20}$",
            message = "Phone number must contain only digits and be between 7 and 20 characters"
    )
    @Column(name = "phone", length = 20)
    private String phone;

    // Código de establecimiento SRI (001, 002...)
    @NotBlank(message = "Establishment code is required")
    @Pattern(regexp = "^[0-9]{3}$", message = "The establishment code must be exactly 3 digits")
    @Column(name = "establishment_code", nullable = false, length = 3, unique = true)
    private String establishmentCode;

    // Punto de emisión (001, 002...)
    @NotBlank(message = "Emission point code is required")
    @Pattern(regexp = "^[0-9]{3}$", message = "The emission point must be exactly 3 digits")
    @Column(name = "emission_point", nullable = false, length = 3)
    private String emissionPoint;



    // Data Persist
    @NotNull(message = "It must be specified whether the user is active or not")
    @Column(name = "is_active")
    private Boolean is_active;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
