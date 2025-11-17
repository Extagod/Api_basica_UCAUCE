package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Active Customers
    @Override
    public List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return personRepository.findAllUnActiveCustomers(last, pageSize);
    }


    // Inactive Customers
    @Override
    public List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return personRepository.findAllActiveCustomers(last, pageSize);
    }




    // Active Employee
    @Override
    public List<PersonPageFullResponseDto> findAllActiveEmployees(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return personRepository.findAllActiveEmployees(last, pageSize);
    }


    // Inactive Employee
    @Override
    public List<PersonPageFullResponseDto> findAllUnActiveEmployees(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return personRepository.findAllUnActiveEmployees(last, pageSize);
    }



    @Override
    public Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number) {
        if (identification_number == null || identification_number.isBlank())
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);

        return personRepository.findPersonByIdentificationNumber(identification_number);
    }
}
