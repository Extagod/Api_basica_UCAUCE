package com.api.supermercado.mappers;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

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
    Customer toEntity(CustomerRegisterDto customerRegisterDto);

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
    CustomerRegisterDto toDto(Customer customer);

}
