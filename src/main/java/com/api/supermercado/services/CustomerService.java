package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;

import java.util.List;

public interface CustomerService {
    // Active Customers
    List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size);

    // Inactive Customers
    List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size);

}
