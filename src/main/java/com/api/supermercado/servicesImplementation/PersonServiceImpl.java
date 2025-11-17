package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.CustomerMapper;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PersonRequestMapper personRequestMapper;


    @Override
    public Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Person> updateCustomer(String identificationNumber, CustomerRegisterDto dto) {

        if (identificationNumber == null || identificationNumber.isBlank() || dto == null) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }

        // 1. Obtener Customer existente
        Customer existingCustomer = customerRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        // 2. Validar identificación única si la cambia
        if (!existingCustomer.getIdentificationNumber().equalsIgnoreCase(dto.identificationNumber())
                && personRepository.existsByIdentificationNumber(dto.identificationNumber())) {

            throw new PersonException(PersonExceptions.DUPLICATE_IDENTIFICATION);
        }

        // 3. ACTUALIZAR campos de Person
        personRequestMapper.updatePersonWithDto(dto, existingCustomer);

        // 4. ACTUALIZAR campos específicos de Customer
        existingCustomer.setRegistrationDate(dto.registrationDate());

        // 5. Forzar activo si deseas
        if (dto.is_active() != null)
            existingCustomer.setIs_active(dto.is_active());

        // 6. Guardar y retornar
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return Optional.of(updatedCustomer);
    }
}


