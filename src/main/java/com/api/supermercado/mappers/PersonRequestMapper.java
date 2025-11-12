package com.api.supermercado.mappers;

import com.api.supermercado.dtos.PersonRequestRegistertDto;
import com.api.supermercado.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonRequestMapper {

    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "idIentificationType", target = "idIentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password", ignore = true)

    Person toEntity(PersonRequestRegistertDto personRequestRegistertDto);


    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "idIentificationType", target = "idIentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password", ignore = true)

    PersonRequestRegistertDto toDto(Person person);


}
