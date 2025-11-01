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
    @Mapping(source = "created_at", target = "created_at")
    @Mapping(source = "updated_at", target= "updated_at")
    Product toEntity(ProductRequestDto dto);


    @Mapping(source = "nameProduct", target = "nameProduct")
    @Mapping(source = "descriptionProduct", target = "descriptionProduct")
    @Mapping(source = "stockProduct", target = "stockProduct")
    @Mapping(source = "priceProduct", target = "priceProduct")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "supplierId", target = "supplierId")
    @Mapping(source = "barCode", target = "barCode")
    @Mapping(source = "activeProduct", target ="activeProduct")
    @Mapping(source = "created_at", target = "created_at")
    @Mapping(source = "updated_at", target= "updated_at")
    ProductRequestDto toDto(Product entity);

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "supplierId", target = "supplierId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductRequestDto dto, @MappingTarget Product product);

}
