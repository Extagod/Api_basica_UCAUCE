package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;


import java.util.List;

public interface PersonService {
    List<PersonPageFullResponseDto> findAllAvalavailablePersons(Integer lastPersonId, Integer size);}
