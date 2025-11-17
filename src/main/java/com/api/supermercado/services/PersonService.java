package com.api.supermercado.services;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;


import java.util.List;
import java.util.Optional;

public interface PersonService {


    Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number);
    Optional<Person> updateCustomer(String identification_number, CustomerRegisterDto customerRegisterDto);
}

