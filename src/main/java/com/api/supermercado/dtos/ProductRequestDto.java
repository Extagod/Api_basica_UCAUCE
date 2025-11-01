package com.api.supermercado.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductRequestDto {

    private String nameProduct;
    private String descriptionProduct;
    private Integer stockProduct;
    private BigDecimal priceProduct;
    private Integer categoryId;
    private Integer supplierId;
    private String barCode;
    private  boolean activeProduct;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;


}
