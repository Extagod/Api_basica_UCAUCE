package com.api.supermercado.services;

import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.dtos.BranchRegisterDto;

import java.util.List;

public interface BranchService {
    List<BranchFullResponseDto>listActiveBranches(Integer lastId, Integer size);
    List<BranchFullResponseDto> listInactiveBranches(Integer lastId, Integer size);
    void createBranch(BranchRegisterDto branchRegisterDto);
    void deleteBranch(String establishmentCode);
}
