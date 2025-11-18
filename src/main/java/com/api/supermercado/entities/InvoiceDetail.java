package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_detail_id", nullable = false)
    private Integer id;

    // ---------------- INVOICE ----------------
    @NotNull(message = "Invoice reference is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoiceId;

    // ---------------- PRODUCT ----------------
    @NotNull(message = "Product reference is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // ---------------- QUANTITY ----------------
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 9999, message = "Quantity cannot exceed 9999 units")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // ---------------- UNIT PRICE ----------------
    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", message = "Unit price must be at least 0.01")
    @Digits(integer = 10, fraction = 2, message = "Unit price format is invalid (max 10 digits, 2 decimals)")
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    // ---------------- SUBTOTAL ----------------
    @DecimalMin(value = "0.01", message = "Subtotal must be at least 0.01")
    @Digits(integer = 10, fraction = 2, message = "Subtotal format is invalid (max 10 digits, 2 decimals)")
    @ColumnDefault("((quantity))")
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    // ---------------- TAX VALUE ----------------
    @NotNull(message = "Tax value is required")
    @DecimalMin(value = "0.00", message = "Tax value cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Tax value format is invalid (max 10 digits, 2 decimals)")
    @Column(name = "tax_value", nullable = false)
    private BigDecimal taxValue;
}
