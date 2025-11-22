package com.api.supermercado.dtos;

import java.util.List;

public record ProductDetailDto(
        String codigoPrincipal,
        String descripcion,
        String cantidad,
        String precioUnitario,
        String precioTotalSinImpuesto,
        List<TaxDTO> impuestos
) {
}
