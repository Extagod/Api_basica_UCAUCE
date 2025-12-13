package com.api.supermercado.dtos;

public record PublicRequestRegisterDto(
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        String address,
        String phone,
        Integer genderId,
        Integer idIdentificationType,
        String identificationNumber
) {
}
