package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @NotBlank(message = "Category name is required.")
    @Size(max = 100, message = "Category name cannot exceed 100 characters.")
    @Column(name = "name_category", nullable = false, length = 100, unique = true)
    private String nameCategory;

    @Size(max = 200, message = "Description cannot exceed 200 characters.")
    @Column(name = "description_category", length = 200)
    private String descriptionCategory;

    @NotNull(message = "It must be specified whether the category  is active or not")
    @Column(name = "is_active")
    private Boolean is_active;
}
