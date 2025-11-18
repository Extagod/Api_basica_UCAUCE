package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.IssuingCompanyRegisterDto;
import com.api.supermercado.dtos.IssuingCompanyProjection;
import com.api.supermercado.services.IssuingCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issuing-company")
@RequiredArgsConstructor
public class IssuingCompanyController {

    private final IssuingCompanyService issuingCompanyService;

    // ----------------------------------------------------------------------
    // GET ACTIVE COMPANIES
    // ----------------------------------------------------------------------
    @GetMapping("/active")
    public ResponseEntity<?> getActiveIssuingCompanies(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        List<IssuingCompanyProjection> companies =
                issuingCompanyService.listActiveIssuingCompanies(lastId, size);

        if (companies.isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            "The list of active issuing companies is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Active issuing companies retrieved successfully.",
                        companies.size(),
                        companies
                )
        );
    }

    // ----------------------------------------------------------------------
    // GET INACTIVE COMPANIES
    // ----------------------------------------------------------------------
    @GetMapping("/inactive")
    public ResponseEntity<?> getInactiveIssuingCompanies(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        List<IssuingCompanyProjection> companies =
                issuingCompanyService.listInactiveIssuingCompanies(lastId, size);

        if (companies.isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            "The list of inactive issuing companies is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Inactive issuing companies retrieved successfully.",
                        companies.size(),
                        companies
                )
        );
    }

    // ----------------------------------------------------------------------
    // CREATE ISSUING COMPANY
    // ----------------------------------------------------------------------
    @PostMapping("/add")
    public ResponseEntity<?> addIssuingCompany(
            @Valid @RequestBody IssuingCompanyRegisterDto dto
    ) {
        issuingCompanyService.createIssuingCompany(dto);
        return ResponseEntity.ok(Map.of(
                "message", "Issuing company successfully created"
        ));
    }

    // ----------------------------------------------------------------------
    // SOFT DELETE ISSUING COMPANY
    // ----------------------------------------------------------------------
    @PutMapping("/logical-delete")
    public ResponseEntity<?> logicalDelete(
            @RequestParam String establishmentCode
    ) {
        issuingCompanyService.deleteIssuingCompany(establishmentCode);
        return ResponseEntity.ok(Map.of(
                "message", "Issuing company successfully deleted"
        ));
    }

    // ----------------------------------------------------------------------
    // UPDATE ISSUING COMPANY
    // ----------------------------------------------------------------------
    @PutMapping("/update")
    public ResponseEntity<?> updateIssuingCompany(
            @RequestParam String establishmentCode,
            @Valid @RequestBody IssuingCompanyRegisterDto dto
    ) {

        return issuingCompanyService.updateIssuingCompany(establishmentCode, dto)
                .map(updated -> ResponseEntity.ok(Map.of(
                        "message", "Issuing company successfully updated",
                        "company", updated
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
