package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer idProduct;

    @NotBlank(message = "Product name is required.")
    @Size(max = 100, message = "Product name cannot exceed 100 characters.")
    @Column(name = "name", nullable = false, length = 100)
    private String nameProduct;

    @Size(max = 200, message = "Product description cannot exceed 200 characters.")
    @Column(name = "description", length = 200)
    private String descriptionProduct;

    @NotNull(message = "Product price is required.")
    @DecimalMin(value = "0.01", message = "Product price must be greater than 0.")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 digits and 2 decimal places.")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceProduct;

    @NotNull(message = "Stock value is required.")
    @Min(value = 0, message = "Stock cannot be negative.")
    @ColumnDefault("0")
    @Column(name = "stock", nullable = false)
    private Integer stockProduct;

    @NotBlank(message = "Bar code is required.")
    @Pattern(
            regexp = "^[0-9]{8,13}$",
            message = "Bar code must contain 8 to 13 numeric digits (EAN-8 or EAN-13 format)."
    )
    @Column(name = "bar_code", nullable = false, unique = true, length = 13)
    private String barCode;

    @NotNull(message = "Category ID is required.")
    @Positive(message = "Category ID must be greater than 0.")
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;



    @NotNull(message = "Supplier ID is required.")
    @Positive(message = "Supplier ID must be greater than 0.")
    @Column(name = "supplier_id", nullable = false)
    private Integer supplierId;


    @NotNull(message = "Has tax is required.")
    @Column(name = "has_tax", nullable = false)
    private Boolean hasTax;


    //Data persist
    @NotNull(message = "Product active state is required.")
    @ColumnDefault("true")
    @Column(name = "active_product", nullable = false)
    private Boolean activeProduct;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
