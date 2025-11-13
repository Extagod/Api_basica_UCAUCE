package com.api.supermercado.mappers;

import com.api.supermercado.dtos.PersonRequestRegistertDto;
import com.api.supermercado.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PersonRequestMapper {

    // DTO → ENTITY
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
    @Mapping(
            target = "roleId",
            expression = "java(com.api.supermercado.security.Role.fromId(personRequestRegistertDto.roleId()))"
    )
    Person toEntity(PersonRequestRegistertDto personRequestRegistertDto);

    // ENTITY → DTO
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
    @Mapping(
            target = "roleId",
            expression = "java(person.getRoleId() != null ? person.getRoleId().getId() : null)"
    )
    PersonRequestRegistertDto toDto(Person person);
}
