package com.api.supermercado.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EmployeeRegisterDto(
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
        Integer branchId,
        String position,
        BigDecimal salary,
        LocalDate hireDate,
        List<Integer>roles,
        Boolean is_active
) {}

