package com.api.supermercado.IServices;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPerson implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<PersonPageFullResponseDto> findAllAvalavailablePersons(Integer lastPersonId, Integer size) {
        if (lastPersonId == null ||  lastPersonId < 0) lastPersonId = 0;
        if (size == null || size < 0) size = 10;

        return  personRepository.findPersonsPage(lastPersonId, size);
    }

}
