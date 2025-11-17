package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.dtos.BranchRegisterDto;
import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.services.BranchService;
import com.api.supermercado.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;


    @GetMapping("/allAvailableBranches")
    public ResponseEntity<?> getAllAvailableProducts(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        List<BranchFullResponseDto> branches = branchService.listActiveBranches(lastId, size);

        if (branches.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "The list of active branches is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of active branches was obtained successfully.",
                        branches.size(),
                        branches
                )
        );
    }

    @GetMapping("/allUnAvailableBranches")
    public ResponseEntity<?> getAllUnAvailableProducts(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<BranchFullResponseDto> branches = branchService.listInactiveBranches(lastId, size);

        if (branches.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "The list of inactive branches is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of inactive branches was obtained successfully.",
                        branches.size(),
                        branches
                )
        );
    }

    @PostMapping("/add")
    ResponseEntity<?> addBranch(@Valid @RequestBody BranchRegisterDto branchRegisterDto) {
        branchService.createBranch(branchRegisterDto);
        return  ResponseEntity.ok("Branch successfully created");
    }
}
