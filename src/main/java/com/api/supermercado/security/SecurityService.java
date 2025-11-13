package com.api.supermercado.security;


import com.api.supermercado.dtos.PersonRequestRegistertDto;
import com.api.supermercado.entities.Person;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SecurityService {


    private final PersonRepository personRepository;
    private final PersonRequestMapper personRequestMapper;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(PersonRequestRegistertDto request) {
        if (personRepository.existsByUsername(request.username())) {
            throw  new PersonException(PersonExceptions.DUPLICATE_PERSON_USERNAME);
        }

        if (personRepository.findPersonByIdentificationNumber(request.identificationNumber()).isPresent()) {
            throw  new PersonException(PersonExceptions.DUPLICATE_IDENTIFICATION);
        }

        if (personRepository.findPersonByEmail(request.email()).isPresent()) {
            throw  new PersonException(PersonExceptions.DUPLICATE_PERSON_EMAIL);
        }

        Person person = personRequestMapper.toEntity(request);
        person.setIs_active(true); // Incializamos el valor en true por defecto al momento de que el usuario se registre
        person.setPassword(passwordEncoder.encode(request.password()));
        person.setRoleId(Role.fromId(request.roleId()));
        personRepository.save(person);
    }
}
