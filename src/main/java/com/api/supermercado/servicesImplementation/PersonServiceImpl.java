package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
<<<<<<< HEAD
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
=======
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonPageFullResponseDto> findAllAvailablePersons(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

<<<<<<< HEAD
        return personRepository.findAllAvailablePersons(last, pageSize);
    }

    @Override
    public List<PersonPageFullResponseDto> findAllUnAvailablePersons(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return personRepository.findAllUnAvailablePersons(last, pageSize);
    }

    @Override
    public Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number) {
        if(identification_number == null || identification_number.isBlank())
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);

        return  personRepository.findPersonByIdentificationNumber(identification_number);

=======
        return personRepository.findPersonsPage(last, pageSize);
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb
    }
}
