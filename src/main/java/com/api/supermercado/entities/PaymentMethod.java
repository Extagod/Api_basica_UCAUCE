package com.api.supermercado.entities;

import com.api.supermercado.enums.PaymentTypeEnum;
import com.api.supermercado.enums.CreditStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id", nullable = false)
    private Integer id;

    // -----------------------------------------------------------
    // RELACIÓN CON LA FACTURA
    // -----------------------------------------------------------
    @NotNull(message = "Invoice reference is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    // -----------------------------------------------------------
    // TIPO DE PAGO
    // -----------------------------------------------------------
    @NotNull(message = "Payment type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false, length = 20)
    private PaymentTypeEnum paymentType;

    // -----------------------------------------------------------
    // CAMPOS PARA TRANSFERENCIA
    // -----------------------------------------------------------
    @Size(max = 100)
    @Column(name = "bank", length = 100)
    private String bank;

    @Size(max = 50)
    @Column(name = "account", length = 50)
    private String account;

    @Size(max = 100)
    @Column(name = "reference", length = 100)
    private String reference;

    // -----------------------------------------------------------
    // CAMPOS PARA CRÉDITO
    // -----------------------------------------------------------
    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "credit_days")
    private Integer creditDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status", length = 20)
    private CreditStatusEnum creditStatus = CreditStatusEnum.PENDIENTE;

    // -----------------------------------------------------------
    // CAMPOS GENERALES
    // -----------------------------------------------------------
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Size(max = 500)
    @Column(name = "notes", length = 500)
    private String notes;

    // -----------------------------------------------------------
    // AUDITING
    // -----------------------------------------------------------
    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
