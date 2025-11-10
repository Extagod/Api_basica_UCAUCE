package com.api.supermercado.services;

import com.api.supermercado.dtos.PersonPageFullResponseDto;


import java.util.List;

public interface PersonService {
    List<PersonPageFullResponseDto> findAllAvailablePersons(Integer lastPersonId, Integer size);}
