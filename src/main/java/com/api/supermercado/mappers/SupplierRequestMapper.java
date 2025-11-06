package com.api.supermercado.mappers;

import com.api.supermercado.dtos.SupplierRequestDto;
import com.api.supermercado.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import javax.crypto.spec.PSource;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierRequestMapper {

    SupplierRequestMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(SupplierRequestMapper.class);



    @Mapping(source = "companyName" , target= "companyName")
    @Mapping(source = "taxId" , target= "taxId")
    @Mapping(source = "address" , target= "address")
    @Mapping(source = "phone" , target= "phone")
    @Mapping(source = "email" , target= "email")
    @Mapping(source = "is_active" , target= "is_active")
    Supplier toEntity(SupplierRequestDto dto);


    @Mapping(source = "companyName" , target= "companyName")
    @Mapping(source = "taxId" , target= "taxId")
    @Mapping(source = "address" , target= "address")
    @Mapping(source = "phone" , target= "phone")
    @Mapping(source = "email" , target= "email")
    @Mapping(source = "is_active" , target= "is_active")
    SupplierRequestDto toDto(Supplier entity);
}
