package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.dtos.BranchRegisterDto;
import com.api.supermercado.entities.Branch;
import com.api.supermercado.exceptions.BranchException;
import com.api.supermercado.exceptions.BranchExceptions;
import com.api.supermercado.mappers.BranchMapper;
import com.api.supermercado.repositories.BranchRepository;
import com.api.supermercado.services.BranchService;
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
        Branch existingBranch = branchRepository.findByEstablishmentCodeNative(branchRegisterDto.establishmentCode());
        if (existingBranch != null) {
            throw new BranchException(BranchExceptions.DUPLICATE_ESTABLISHMENT_CODE);
        }

        // Crear entidad desde DTO
        Branch branch = branchMapper.toEntity(branchRegisterDto);

        // Activar sucursal por defecto
        branch.setIs_active(true);

        // Guardar
        branchRepository.save(branch);
    }

    @Override
    public void deleteBranch(String establishmentCode) {

    }

}
