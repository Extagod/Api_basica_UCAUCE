package com.api.supermercado.dtos;

import java.time.Instant;
import java.time.LocalDateTime;

public interface CustomerPageFullResponseDto {

        String getFirstName();
        String getLastName();

        Integer getIdentificationType();
        String getIdentificationNumber();

        String getEmail();
        String getPhone();
        String getAddress();

        String getIsActive();

        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();

        // Campo propio de Customer
        Instant getRegistrationDate();
    }


