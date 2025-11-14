package com.api.supermercado.dtos;

import com.api.supermercado.security.Role;

import java.time.LocalDate;


public record PersonRequestRegisterDto(
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
        Integer roleId
) {
}
