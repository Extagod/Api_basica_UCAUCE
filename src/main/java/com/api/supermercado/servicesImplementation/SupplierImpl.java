package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.SupplierRequestDto;
import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.entities.Supplier;
import com.api.supermercado.exceptions.CategoryException;
import com.api.supermercado.exceptions.CategoryExceptions;
import com.api.supermercado.exceptions.SupplierException;
import com.api.supermercado.exceptions.SupplierExceptions;
import com.api.supermercado.mappers.SupplierRequestMapper;
import com.api.supermercado.repositories.SupplierRepository;
import com.api.supermercado.services.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierRequestMapper supplierRequestMapper;

    @Autowired
    public SupplierImpl(SupplierRepository supplierRepository, SupplierRequestMapper supplierRequestMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierRequestMapper = supplierRequestMapper;
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

    @Override
    public void createSupplier(SupplierRequestDto supplierRequestDto) {

        if (supplierRequestDto == null) {
            throw new SupplierException(SupplierExceptions.INVALID_SUPPLIER_DATA);
        }

        String normalizedTaxId = supplierRequestDto.taxId().trim();

        if (supplierRepository.existsByTaxId(normalizedTaxId)) {
            throw new SupplierException(SupplierExceptions.DUPLICATE_TAX_ID);
        }

        try {
            Supplier supplier = supplierRequestMapper.toEntity(supplierRequestDto);

            supplier.setIs_active(true);
            supplierRepository.save(supplier);

        } catch (SupplierException e) {
            throw e;

        } catch (Exception e) {
            throw new SupplierException(SupplierExceptions.DATABASE_ERROR);
        }
    }

    @Override
    public void deleteSupplier(String taxId) {
        if (taxId == null || taxId.isBlank()) {
            throw new SupplierException(SupplierExceptions.INVALID_SUPPLIER_DATA);
        }

        String normalizedTaxId = taxId.trim();

        Supplier supplier = supplierRepository.findByTaxId(normalizedTaxId)
                .orElseThrow(() -> new SupplierException(SupplierExceptions.SUPPLIER_NOT_FOUND));

        if (!supplier.getIs_active()) {
            throw new SupplierException(SupplierExceptions.SUPPLIER_ALREADY_INACTIVE);
        }


        supplier.setIs_active(false);

        supplierRepository.save(supplier);
    }

    @Transactional
    @Override
    public Optional<Supplier> updateSupplier(String taxId, SupplierRequestDto dto) {
        if (dto == null) {
            throw new SupplierException(SupplierExceptions.INVALID_SUPPLIER_DATA);
        }
        Supplier supplier = new Supplier();
        supplier.setCompanyName(dto.companyName());
        supplier.setTaxId(dto.taxId());
        supplier.setAddress(dto.address());
        supplier.setPhone(dto.phone());
        supplier.setEmail(dto.email());
        supplier.setIs_active(dto.is_active());

        String normalizedTaxId = taxId.trim();

        if (!supplier.getTaxId().equalsIgnoreCase(dto.taxId())) {
            boolean duplicateExists = supplierRepository.existsByTaxId(dto.taxId());
            if (duplicateExists) {
                throw new SupplierException(SupplierExceptions.DUPLICATE_TAX_ID);
            }
        }
        return Optional.of(supplierRepository.save(supplier));
    }

    @Override
    public Optional<Supplier> getSupplier(String taxId) {

        if (taxId == null || taxId.isBlank()) {
            throw new SupplierException(SupplierExceptions.INVALID_SUPPLIER_DATA);
        }

        String normalizedTaxId = taxId.trim();

        try {
            return supplierRepository.findByTaxId(normalizedTaxId)
                    .map(Optional::of)
                    .orElseThrow(() -> new SupplierException(SupplierExceptions.SUPPLIER_NOT_FOUND));

        } catch (SupplierException e) {
            throw e;

        } catch (Exception e) {
            throw new SupplierException(SupplierExceptions.DATABASE_ERROR);
        }
    }

}
