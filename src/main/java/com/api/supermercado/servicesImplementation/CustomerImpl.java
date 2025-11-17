package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.CustomerPageFullResponseDto;
import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PersonRequestMapper personRequestMapper;

    // Active Customers
    @Override
    public List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return customerRepository.findAllActiveCustomers(last, pageSize);
    }

    // Inactive Customers
    @Override
    public List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return customerRepository.findAllUnActiveCustomers(last, pageSize);
    }

    @Transactional
    @Override
    public Optional<Person> updateCustomer(String identificationNumber, CustomerRegisterDto dto) {

        if (identificationNumber == null || identificationNumber.isBlank() || dto == null) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }

        Customer existing = customerRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        if (!existing.getIdentificationNumber().equalsIgnoreCase(dto.identificationNumber()) &&
                personRepository.existsByIdentificationNumber(dto.identificationNumber())) {
            throw new PersonException(PersonExceptions.DUPLICATE_IDENTIFICATION);
        }

        personRequestMapper.updatePersonWithDto(dto, existing);

        if (dto.registrationDate() != null) {
            existing.setRegistrationDate(dto.registrationDate());
        }

        Customer updated = customerRepository.save(existing);

        return Optional.of(updated);
    }

    @Override
    public void deleteCustomer(String identificationNumber) {
        if(identificationNumber == null || identificationNumber.isBlank()){
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }
        Customer existing = customerRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        existing.setIs_active(false);
    }

    @Override
    public Optional<CustomerPageFullResponseDto> findEmployeeByIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.isBlank()) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }

        return customerRepository.findCustomerByIdentificationNumber(identificationNumber)
                .stream()
                .findFirst();
    }

}

