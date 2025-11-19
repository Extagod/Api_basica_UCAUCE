package com.api.supermercado.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

public record InvoiceRequestDto(

        // FOREIGN KEYS
        @NotNull(message = "Issuing company is required")
        Integer issuingCompanyId,

        @NotNull(message = "Customer is required")
        Integer customerId,

        @NotNull(message = "Creator user (employee) is required")
        Integer creatorUserId,

        // DATOS PRINCIPALES
        @NotNull(message = "Issue date is required")
        @PastOrPresent(message = "Issue date cannot be in the future")
        Instant issueDate,

        @NotBlank(message = "Access key is required")
        @Size(min = 10, max = 49, message = "Access key must have between 10 and 49 characters")
        String accessKey,

        @NotBlank(message = "Sequential number is required")
        @Size(min = 3, max = 20, message = "Sequential must be between 3 and 20 characters")
        String sequential,

        @NotBlank(message = "Invoice status is required")
        String status,

        // TOTALES
        @NotNull(message = "Subtotal without VAT is required")
        @DecimalMin(value = "0.00", message = "Subtotal cannot be negative")
        @Digits(integer = 10, fraction = 2, message = "Invalid format for subtotal")
        BigDecimal subtotalWithoutTax,

        @NotNull(message = "VAT total is required")
        @DecimalMin(value = "0.00", message = "Total VAT cannot be negative")
        @Digits(integer = 10, fraction = 2, message = "Invalid format for VAT")
        BigDecimal totalVat,

        @NotNull(message = "Total with taxes is required")
        @DecimalMin(value = "0.00", message = "Total cannot be negative")
        @Digits(integer = 10, fraction = 2, message = "Invalid format for total")
        BigDecimal totalWithTax,

        // DOCUMENTOS
        String xmlDocument,
        String xmlSigned,
        byte[] ridePdf,

        // SRI
        String authorizationNumber,
        Instant authorizationDate,
        String sriStatus,
        String sriMessages,
        Instant sriSentDate,
        Instant sriResponseDate,
        String sriAuthorizationNumber,
        Instant sriAuthorizationDate,

        // OTROS
        String originalData,
        Boolean isRegisteredInCashRegister

) {}
