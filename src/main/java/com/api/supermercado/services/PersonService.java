package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;


import java.util.List;
import java.util.Optional;

public interface PersonService {
    // Active Customers
    List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size);

    // Inactive Customers
    List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size);

    // Active Employee
    List<PersonPageFullResponseDto> findAllActiveEmployees(Integer lastPersonId, Integer size);

    // Inactive Employee
    List<PersonPageFullResponseDto> findAllUnActiveEmployees(Integer lastPersonId, Integer size);

    Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number);
    
}

