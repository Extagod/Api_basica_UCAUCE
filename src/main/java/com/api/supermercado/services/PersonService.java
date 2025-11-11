package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;


import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonPageFullResponseDto> findAllAvailablePersons(Integer lastPersonId, Integer size);
    List<PersonPageFullResponseDto> findAllUnAvailablePersons(Integer lastPersonId, Integer size);
    Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number);
}


