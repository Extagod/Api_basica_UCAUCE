package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface PersonPageFullResponseDto {
    Integer getId();
    Integer getIdentificationTypeId();
    String getIdentificationTypeDescription();
    String getIdentificationNumber();
    String getFirstName();
    String getLastName();
    Integer getGenderId();
    String getGenderDescription();
    String getEmail();
    String getPhone();
    String getAddress();
    String getIsActive();
    java.time.LocalDate getBirthDate();
    java.time.LocalDateTime getCreatedAt();
    java.time.LocalDateTime getUpdatedAt();
}
