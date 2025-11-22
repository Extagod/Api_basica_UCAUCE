package com.api.supermercado.dtos;

public record TaxDTO(
        String codigo,
        String codigoPorcentaje,
        String tarifa,
        String baseImponible,
        String valor) {
}
