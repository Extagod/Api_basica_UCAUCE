package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface ProductPageResponseDto {
    String getProductName();
    String getProductDescription();
    BigDecimal getPrice();
    Integer getStock();
    String getCategoryName();
    String getSupplierName();
    String getBarCode();
    Boolean getActiveProduct();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    Boolean getHasTax();
}
