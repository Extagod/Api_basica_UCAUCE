package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;


import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

public interface PersonService {
    List<PersonPageFullResponseDto> findAllAvailablePersons(Integer lastPersonId, Integer size);
    List<PersonPageFullResponseDto> findAllUnAvailablePersons(Integer lastPersonId, Integer size);
    Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identification_number);
}


=======

public interface PersonService {
    List<PersonPageFullResponseDto> findAllAvailablePersons(Integer lastPersonId, Integer size);}
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb
