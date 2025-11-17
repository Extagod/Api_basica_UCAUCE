package com.api.supermercado.controller;

import com.api.supermercado.dtos.*;
import com.api.supermercado.services.BranchService;
import com.api.supermercado.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseProduct(@Valid @RequestParam String establishmentCode) {
        branchService.deleteBranch(establishmentCode);
        return ResponseEntity.ok(Map.of(
                "message", "Branch successfully deleted"
        ));
    }
    @PutMapping("/updateBranch")
    public ResponseEntity<?> updateBranch(
            @Valid
            @RequestParam String establishmentCode,
            @RequestBody BranchRegisterDto branchRegisterDto) {

        return branchService.UpdateBranch(establishmentCode, branchRegisterDto)
                .map(updateBranch -> ResponseEntity.ok(Map.of(
                        "message", "Product successfully updated",
                        "product", updateBranch
                )))
                .orElse(ResponseEntity.notFound().build());
    }

}
