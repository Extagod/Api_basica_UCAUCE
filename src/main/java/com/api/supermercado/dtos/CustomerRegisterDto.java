package com.api.supermercado.dtos;

import java.time.Instant;
import java.time.LocalDate;

public record CustomerRegisterDto(
        String firstName,
        String lastName,
        Integer idIdentificationType,
        String identificationNumber,
        Integer genderId,
        LocalDate birthDate,
        String address,
        String phone,
        String email,
        String username,
        String password,
        Instant registrationDate

)
{}
