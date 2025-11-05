package com.api.supermercado.mappers;

import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Product;
import org.mapstruct.*;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductRequestMapper {

    ProductRequestMapper INSTANCE = Mappers.getMapper(ProductRequestMapper.class);

    @Mapping(source = "nameProduct", target = "nameProduct")
    @Mapping(source = "descriptionProduct", target = "descriptionProduct")
    @Mapping(source = "stockProduct", target = "stockProduct")
    @Mapping(source = "priceProduct", target = "priceProduct")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "supplierId", target = "supplierId")
    @Mapping(source = "barCode", target = "barCode")
    @Mapping(source = "activeProduct", target ="activeProduct")
    Product toEntity(ProductRequestDto dto);


    @Mapping(source = "nameProduct", target = "nameProduct")
    @Mapping(source = "descriptionProduct", target = "descriptionProduct")
    @Mapping(source = "stockProduct", target = "stockProduct")
    @Mapping(source = "priceProduct", target = "priceProduct")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "supplierId", target = "supplierId")
    @Mapping(source = "barCode", target = "barCode")
    @Mapping(source = "activeProduct", target ="activeProduct")
    ProductRequestDto toDto(Product entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "supplierId", target = "supplierId")
    void updateProductFromDto(ProductRequestDto dto, @MappingTarget Product product);

}
