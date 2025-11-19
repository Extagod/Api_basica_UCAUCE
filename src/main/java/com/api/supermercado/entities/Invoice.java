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
    // FOREIGN KEYS
    // ---------------------------------------------------------------------

    @NotNull(message = "Issuing company is required")
    @Column(name = "issuing_company_id", nullable = false)
    private Integer issuingCompanyId;

    @NotNull(message = "Customer is required")
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @NotNull(message = "Employee is required")
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    // ---------------------------------------------------------------------
    // DATOS PRINCIPALES
    // ---------------------------------------------------------------------

    @NotNull(message = "Issue date is required")
    @PastOrPresent
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;

    @NotBlank(message = "Access key is required")
    @Size(min = 10, max = 49)
    @Column(name = "access_key", nullable = false, length = 49, unique = true)
    private String accessKey;

    @NotBlank(message = "Sequential number is required")
    @Size(min = 3, max = 20)
    @Column(name = "sequential", nullable = false, length = 20)
    private String sequential;

    @NotBlank(message = "Invoice number is required")
    @Size(min = 15, max = 17)
    @Column(name = "invoice_number", nullable = false, length = 20, unique = true)
    private String invoiceNumber;

    @NotBlank(message = "Invoice status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    // ---------------------------------------------------------------------
    // TOTALES
    // ---------------------------------------------------------------------

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "subtotal_without_tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotalWithoutTax;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_vat", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVat;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_with_tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalWithTax;

    // ---------------------------------------------------------------------
    // DOCUMENTOS
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
    // SRI
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
    // OTROS
    // ---------------------------------------------------------------------

    @Lob
    @Column(name = "original_data")
    private String originalData;

    @Column(name = "is_registered_in_cash_register", nullable = false)
    private Boolean isRegisteredInCashRegister = false;
}
