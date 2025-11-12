package com.api.supermercado.dtos;

import java.time.LocalDate;


public record PersonRequestRegistertDto(
        String firstName,
        String lastName,
        Integer idIentificationType,
        String identificationNumber,
        Integer genderId,
        LocalDate birthDate,
        String address,
        String phone,
        String email,
        String username,
        String password
) {
}
