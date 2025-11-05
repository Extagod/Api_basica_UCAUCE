package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return personRepository.findPersonsPage(last, pageSize);
    }
}
