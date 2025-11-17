package com.api.supermercado.services;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeePageFullResponseDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Person;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    // Active Employee
    List<PersonPageFullResponseDto> findAllActiveEmployees(Integer lastPersonId, Integer size);

    // Inactive Employee
    List<PersonPageFullResponseDto> findAllUnActiveEmployees(Integer lastPersonId, Integer size);

    Optional<Person> updateEmployee(String identificationNumber, EmployeeRegisterDto dto);
    void deleteEmployee(String identificationNumber);
    Optional<EmployeePageFullResponseDto> findEmployeeByIdentificationNumber(String identificationNumber);
}
