package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.entities.Supplier;
import com.api.supermercado.repositories.SupplierRepository;
import com.api.supermercado.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public List<SupplierResponseDto> findActiveSuppliers(Integer lastId, Integer size) {
        if(lastId == null) lastId = 0;
        if(size == null || size <= 0) size = 10;
        return  supplierRepository.findActiveSuppliers(lastId, size);
    }

    @Override
    public List<SupplierResponseDto> findInactiveSuppliers(Integer lastId, Integer size) {

        if(lastId == null) lastId = 0;
        if(size == null || size <= 0) size = 10;
        return  supplierRepository.findInActiveSuppliers(lastId, size);
    }

}
