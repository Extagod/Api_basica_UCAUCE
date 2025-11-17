package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.dtos.BranchRegisterDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Branch;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.BranchException;
import com.api.supermercado.exceptions.BranchExceptions;
import com.api.supermercado.exceptions.ProductException;
import com.api.supermercado.exceptions.ProductExceptions;
import com.api.supermercado.mappers.BranchMapper;
import com.api.supermercado.repositories.BranchRepository;
import com.api.supermercado.services.BranchService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;


    @Override
    public List<BranchFullResponseDto> listActiveBranches(Integer lastId, Integer size) {
        if(lastId == null) lastId = 0;
        if(size == null || size <= 0) size = 10;
        return branchRepository.findActiveBranches(lastId, size);
    }


    @Override
    public List<BranchFullResponseDto> listInactiveBranches(Integer lastId, Integer size) {
        if(lastId == null) lastId = 0;
        if(size == null || size <= 0) size = 10;
        return branchRepository.findInactiveBranches(lastId, size);
    }

    @Override
    public void createBranch(BranchRegisterDto branchRegisterDto) {

        if (branchRegisterDto == null) {
            throw new BranchException(BranchExceptions.INVALID_BRANCH_DATA);
        }

        // Validar duplicado por establishment code
        Branch existingBranch = branchRepository.findByEstablishmentCode(branchRegisterDto.establishmentCode())
                .orElseThrow(()-> new BranchException(BranchExceptions.DUPLICATE_ESTABLISHMENT_CODE));

        // Crear entidad desde DTO
        Branch branch = branchMapper.toEntity(branchRegisterDto);

        // Activar sucursal por defecto
        branch.setIs_active(true);

        // Guardar
        branchRepository.save(branch);
    }

    @Override
    public void deleteBranch(String establishmentCode) {
        if(establishmentCode == null || establishmentCode.isBlank()){
            throw new BranchException(BranchExceptions.INVALID_BRANCH_DATA);
        }
        Branch branch = branchRepository.findByEstablishmentCode(establishmentCode)
                .orElseThrow(() -> new ProductException(ProductExceptions.PRODUCT_NOT_FOUND));
        branch.setIs_active(false);
        branchRepository.save(branch);

    }
    @Transactional
    @Override
    public Optional<Branch> UpdateBranch(String establishmentCode, BranchRegisterDto branchRegisterDto) {
        if(establishmentCode == null || establishmentCode.isBlank()){
            throw new BranchException(BranchExceptions.INVALID_BRANCH_DATA);
        }
        Branch existingBranch = branchRepository.findByEstablishmentCode(establishmentCode)
                .orElseThrow(() -> new BranchException(BranchExceptions.BRANCH_NOT_FOUND));

        if (!existingBranch.getEstablishmentCode().equalsIgnoreCase(branchRegisterDto.establishmentCode())
                && branchRepository.existsBranchByEstablishmentCode((branchRegisterDto.establishmentCode()))) {
            throw new ProductException(ProductExceptions.DUPLICATE_PRODUCT);
        }

        branchMapper.updateBranchFromDto(branchRegisterDto, existingBranch);

        Branch updated = branchRepository.save(existingBranch);

        return Optional.of(updated);
    }



}
