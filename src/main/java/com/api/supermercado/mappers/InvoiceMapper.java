package com.api.supermercado.mappers;

import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;
import com.api.supermercado.dtos.InvoiceFullResponseProjection;
import com.api.supermercado.entities.Invoice;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    // ------------------ REQUEST → ENTITY ------------------
    @Mapping(target = "id", ignore = true)
    Invoice toEntity(InvoiceRequestDto dto);

    // ------------------ UPDATE (PATCH/PUT) ------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Invoice entity, InvoiceRequestDto dto);

    // ------------------ PROJECTION → RESPONSE DTO ------------------
    InvoiceResponseDto toResponse(InvoiceFullResponseProjection projection);
}
