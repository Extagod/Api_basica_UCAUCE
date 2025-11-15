package com.api.supermercado.mappers;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.PersonRequestRegisterDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;
import com.api.supermercado.entities.Role;
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
    @Mapping(source = "idIdentificationType", target = "idIdentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password", ignore = true)
    @Mapping(target = "is_active", ignore = true)
    @Mapping(target = "roles", source = "roles")

    Person toEntity(PersonRequestRegisterDto personRequestRegisterDto);
    default Role mapRole(Integer id) {
        Role r = new Role();
        r.setRoleId(id);
        return r;
    }

    // ENTITY → DTO
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "idIdentificationType", target = "idIdentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password", ignore = true)
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "is_active", ignore = true)

    PersonRequestRegisterDto toDto(Person person);
    default Integer mapRole(Role role) {
        return role.getRoleId();
    }

}
