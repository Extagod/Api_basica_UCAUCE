package com.api.supermercado.mappers;

import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EmployeeMapper {
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "idIdentificationType", target = "idIdentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "position", target = "position")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "hireDate", target = "hireDate")
    @Mapping(source = "branchId", target = "branchId")

    Employee toEntity(EmployeeRegisterDto employeeRegisterDto);

    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "idIdentificationType", target = "idIdentificationType")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "genderId", target = "genderId")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "position", target = "position")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "hireDate", target = "hireDate")
    @Mapping(source = "branchId", target = "branchId")

    EmployeeRegisterDto toDto(Employee employee);
}
