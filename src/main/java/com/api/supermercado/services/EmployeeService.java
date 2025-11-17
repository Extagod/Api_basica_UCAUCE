package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;

import java.util.List;

public interface EmployeeService {
    // Active Employee
    List<PersonPageFullResponseDto> findAllActiveEmployees(Integer lastPersonId, Integer size);

    // Inactive Employee
    List<PersonPageFullResponseDto> findAllUnActiveEmployees(Integer lastPersonId, Integer size);
}
