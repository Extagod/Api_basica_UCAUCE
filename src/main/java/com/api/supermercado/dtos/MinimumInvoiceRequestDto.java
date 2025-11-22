package com.api.supermercado.dtos;

import java.util.List;

public record MinimumInvoiceRequestDto(
        TaxInfoDTO taxInfoDTO,
        InvoiceInfoDTO invoiceInfoDTO,
        List<ProductDetailDto>detalles

) {
}
