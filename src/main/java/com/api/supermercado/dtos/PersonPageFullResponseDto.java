package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface PersonPageFullResponseDto {
    Integer getIdentificationType();
    String getIdentificationNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    String getAddress();
    String getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
