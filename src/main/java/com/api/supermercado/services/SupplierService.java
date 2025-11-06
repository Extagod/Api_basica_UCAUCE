package com.api.supermercado.services;

import com.api.supermercado.dtos.SupplierRequestDto;
import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierResponseDto> findActiveSuppliers(Integer lastId, Integer size);
    List<SupplierResponseDto> findInactiveSuppliers(Integer lastId, Integer size);
    void createSupplier(SupplierRequestDto supplierRequestDto);
    void deleteSupplier(String taxId);
    Optional<Supplier> updateSupplier(String taxId, SupplierRequestDto dto);
    Optional<Supplier> getSupplier(String taxId);
}


