package com.api.supermercado.services;

import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<SupplierResponseDto> findActiveSuppliers(Integer lastId, Integer size);
    List<SupplierResponseDto> findInactiveSuppliers(Integer lastId, Integer size);
}
