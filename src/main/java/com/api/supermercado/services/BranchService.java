package com.api.supermercado.services;

import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.dtos.BranchRegisterDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    List<BranchFullResponseDto>listActiveBranches(Integer lastId, Integer size);
    List<BranchFullResponseDto> listInactiveBranches(Integer lastId, Integer size);
    void createBranch(BranchRegisterDto branchRegisterDto);
    void deleteBranch(String establishmentCode);
    Optional<Branch> UpdateBranch(String establishmentCode, BranchRegisterDto branchRegisterDto);
}
