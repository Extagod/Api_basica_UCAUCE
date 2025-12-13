package com.api.supermercado.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface PersonPageFullResponseDto {

    // CAMPOS DE PERSON (PADRE)
    Integer getId();
    Integer getIdentificationTypeId();
    String getIdentificationTypeDescription();
    String getIdentificationNumber();
    String getFirstName();
    String getLastName();
    Integer getGenderId();
    String getGenderDescription();
    String getAddress();
    String getPhone();
    String getEmail();
    String getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getPersonType();

    // CAMPOS DE EMPLOYEE (HIJO)
    Integer getBranchId();
    String getPosition();
    BigDecimal getSalary();
    LocalDate getHireDate();

    // CAMPOS DE CUSTOMER (HIJO)
    Instant getRegistrationDate();
}
