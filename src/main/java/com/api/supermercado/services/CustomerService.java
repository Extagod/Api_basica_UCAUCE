package com.api.supermercado.services;

import com.api.supermercado.dtos.CustomerPageFullResponseDto;
import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeePageFullResponseDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    // Active Customers
    List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size);
    // Inactive Customers
    List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size);
    //Update Customer
    Optional<Person> updateCustomer(String identification_number, CustomerRegisterDto customerRegisterDto);
    void deleteCustomer(String identificationNumber);
    Optional<CustomerPageFullResponseDto> findEmployeeByIdentificationNumber(String identificationNumber);

}
