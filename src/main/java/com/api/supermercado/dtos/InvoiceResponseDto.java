package com.api.supermercado.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public record InvoiceResponseDto(
        Integer id,

        // Foreign Keys
        Integer issuingCompanyId,
        Integer customerId,

        // Main Invoice Data
        Instant issueDate,
        String accessKey,
        String sequential,
        String status,

        // Totals
        BigDecimal subtotalWithoutTax,
        BigDecimal totalVat,
        BigDecimal totalWithTax,

        // Documents
        String xmlDocument,
        String xmlSigned,
        byte[] ridePdf,

        // SRI Authorization
        String authorizationNumber,
        Instant authorizationDate,
        String sriStatus,
        String sriMessages,
        Instant sriSentDate,
        Instant sriResponseDate,
        String sriAuthorizationNumber,
        Instant sriAuthorizationDate,

        // Misc
        String originalData,
        Boolean isRegisteredInCashRegister
) {
}
