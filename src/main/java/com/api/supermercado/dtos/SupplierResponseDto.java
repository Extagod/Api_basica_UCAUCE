package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface SupplierResponseDto {
    Integer getSupplierId();
    String companyName();
    String getAddress();
    String getPhone();
    String getEmail();
    Boolean getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
