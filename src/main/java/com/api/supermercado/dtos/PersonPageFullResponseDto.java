package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface PersonPageFullResponseDto {
    Integer getId();

    Integer getIdentificationTypeId();          // ID del tipo
    String getIdentificationTypeDescription();  // Descripción del tipo

    String getIdentificationNumber();
    String getFirstName();
    String getLastName();

    Integer getGenderId();                      // ID del género
    String getGenderDescription();              // Descripción del género

    String getEmail();
    String getPhone();
    String getAddress();
    String getIsActive();
    java.time.LocalDate getBirthDate();
    java.time.LocalDateTime getCreatedAt();
    java.time.LocalDateTime getUpdatedAt();
}
