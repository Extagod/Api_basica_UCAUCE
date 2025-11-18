package com.api.supermercado.mappers;

import com.api.supermercado.dtos.IssuingCompanyRegisterDto;
import com.api.supermercado.entities.IssuingCompany;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IssuingCompanyMapper {

    // ------------ RECORD ➝ ENTITY ------------
    @Mapping(source = "ruc", target = "ruc")
    @Mapping(source = "legalName", target = "legalName")
    @Mapping(source = "tradeName", target = "tradeName")
    @Mapping(source = "headquartersAddress", target = "headquartersAddress")
    @Mapping(source = "establishmentAddress", target = "establishmentAddress")
    @Mapping(source = "establishmentCode", target = "establishmentCode")
    @Mapping(source = "emissionPoint", target = "emissionPoint")
    @Mapping(source = "environmentType", target = "environmentType")
    @Mapping(source = "emissionType", target = "emissionType")
    @Mapping(source = "requiresAccounting", target = "requiresAccounting")
    @Mapping(source = "specialTaxpayerNumber", target = "specialTaxpayerNumber")
    @Mapping(source = "notificationEmail", target = "notificationEmail")
    @Mapping(source = "certificate", target = "certificate")
    @Mapping(source = "certificatePassword", target = "certificatePassword")
    IssuingCompany toEntity(IssuingCompanyRegisterDto dto);


    // ------------ UPDATE ONLY NON-NULL FIELDS ------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompanyFromDto(IssuingCompanyRegisterDto dto, @MappingTarget IssuingCompany issuingCompany);

}
