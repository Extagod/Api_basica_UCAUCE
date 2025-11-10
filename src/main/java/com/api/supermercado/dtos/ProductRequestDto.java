package com.api.supermercado.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public record ProductRequestDto(
        String nameProduct,
        String descriptionProduct,
        Integer stockProduct,
        BigDecimal priceProduct,
        Integer categoryId,
        Integer supplierId,
        String barCode,
        Boolean activeProduct
) {}

