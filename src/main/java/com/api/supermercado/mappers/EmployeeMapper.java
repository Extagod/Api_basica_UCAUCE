package com.api.supermercado.mappers;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Employee;
import com.api.supermercado.entities.Role;
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
    @Mapping(target = "is_active", ignore = true)
    @Mapping(target = "roles", source = "roles")


    Employee toEntity(EmployeeRegisterDto employeeRegisterDto);
    default Role mapRole(Integer id) {
        Role r = new Role();
        r.setRoleId(id);
        return r;
    }

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
    @Mapping(target = "is_active", ignore = true)
    @Mapping(target = "roles", source = "roles")


    EmployeeRegisterDto toDto(Employee employee);

    default Integer mapRole(Role role) {
        return role.getRoleId();
    }
}
