package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    // ---------------------------------------------------------------------
    // FOREIGN KEYS MANEJADAS SOLO COMO PK (sin relaciones JPA)
    // ---------------------------------------------------------------------

    @NotNull(message = "Issuing company is required")
    @Column(name = "issuing_company_id", nullable = false)
    private Integer issuingCompanyId;

    @NotNull(message = "Customer is required")
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @NotNull(message = "Creator user (employee) is required")
    @Column(name = "creator_user_id", nullable = false)
    private Integer creatorUserId;


    // ---------------------------------------------------------------------
    // DATOS PRINCIPALES DE LA FACTURA
    // ---------------------------------------------------------------------

    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date cannot be in the future")
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;

    @NotBlank(message = "Access key is required")
    @Size(min = 10, max = 49, message = "Access key must have between 10 and 49 characters")
    @Column(name = "access_key", nullable = false, length = 49, unique = true)
    private String accessKey;

    @NotBlank(message = "Sequential number is required")
    @Size(min = 3, max = 20, message = "Sequential must be between 3 and 20 characters")
    @Column(name = "sequential", nullable = false, length = 20)
    private String sequential;

    @NotBlank(message = "Invoice status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status;


    // ---------------------------------------------------------------------
    // TOTALES
    // ---------------------------------------------------------------------

    @NotNull(message = "Subtotal without VAT is required")
    @DecimalMin(value = "0.00", message = "Subtotal cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid format for subtotal")
    @Column(name = "subtotal_without_tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotalWithoutTax;

    @NotNull(message = "VAT total is required")
    @DecimalMin(value = "0.00", message = "Total VAT cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid format for VAT")
    @Column(name = "total_vat", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVat;

    @NotNull(message = "Total with taxes is required")
    @DecimalMin(value = "0.00", message = "Total cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid format for total")
    @Column(name = "total_with_tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalWithTax;


    // ---------------------------------------------------------------------
    // DOCUMENTOS (XML, XML FIRMADO, RIDE PDF)
    // ---------------------------------------------------------------------

    @Lob
    @Column(name = "xml_document")
    private String xmlDocument;

    @Lob
    @Column(name = "xml_signed")
    private String xmlSigned;

    @Lob
    @Column(name = "ride_pdf")
    private byte[] ridePdf;


    // ---------------------------------------------------------------------
    // DATOS DE AUTORIZACIÓN SRI
    // ---------------------------------------------------------------------

    @Column(name = "authorization_number", length = 64)
    private String authorizationNumber;

    @Column(name = "authorization_date")
    private Instant authorizationDate;

    @Column(name = "sri_status", length = 30)
    private String sriStatus;

    @Lob
    @Column(name = "sri_messages")
    private String sriMessages;

    @Column(name = "sri_sent_date")
    private Instant sriSentDate;

    @Column(name = "sri_response_date")
    private Instant sriResponseDate;

    @Column(name = "sri_authorization_number", length = 64)
    private String sriAuthorizationNumber;

    @Column(name = "sri_authorization_date")
    private Instant sriAuthorizationDate;


    // ---------------------------------------------------------------------
    // OTROS DATOS
    // ---------------------------------------------------------------------

    @Lob
    @Column(name = "original_data")
    private String originalData;

    @Column(name = "is_registered_in_cash_register", nullable = false)
    private Boolean isRegisteredInCashRegister = false;
}
