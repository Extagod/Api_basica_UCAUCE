package com.api.supermercado.dtos;

import java.time.LocalDate;
import java.util.List;


public record PersonRequestRegisterDto(
        String firstName,
        String lastName,
        Integer idIdentificationType,
        String identificationNumber,
        Integer genderId,
        String address,
        String phone,
        String email,
        String username,
        String password,
        Boolean is_active,
        List<Integer> roles
) {
}
