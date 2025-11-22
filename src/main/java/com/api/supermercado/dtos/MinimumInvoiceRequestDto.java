package com.api.supermercado.dtos;

public record MinimumInvoiceRequestDto(
        TaxInfoDTO taxInfoDTO,
        InvoiceInfoDTO invoiceInfoDTO


) {
}
