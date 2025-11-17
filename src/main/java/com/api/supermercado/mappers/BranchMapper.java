package com.api.supermercado.mappers;


import com.api.supermercado.dtos.BranchRegisterDto;
import com.api.supermercado.entities.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BranchMapper {
    @Mapping(source = "nameBranch", target = "nameBranch")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "establishmentCode", target = "establishmentCode")
    @Mapping(source = "emissionPoint", target = "emissionPoint")
    BranchRegisterDto toDto(Branch branch);


    @Mapping(source = "nameBranch", target = "nameBranch")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "establishmentCode", target = "establishmentCode")
    @Mapping(source = "emissionPoint", target = "emissionPoint")
    Branch toEntity(BranchRegisterDto branchRegisterDto);
}
