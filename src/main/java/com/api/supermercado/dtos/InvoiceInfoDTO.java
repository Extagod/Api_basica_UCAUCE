package com.api.supermercado.dtos;

public record InvoiceInfoDTO(
        String issueDate,
        String identificationType,
        String identificationNumber,
        String buyerName,
        String subtotalWithoutTax,
        String totalWithTax
) {
}
