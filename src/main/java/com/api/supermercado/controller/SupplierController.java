package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @GetMapping("/allAvailableSuppliers")
    public ResponseEntity<?> getAllAvailableProducts(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<SupplierResponseDto> suppliers = supplierService.findActiveSuppliers(lastId,size);

        // Add null-safety check
        if (suppliers == null || suppliers.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "The list of active suppliers is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of active suppliers was obtained successfully.",
                        suppliers.size(),
                        suppliers
                )
        );
    }

    @GetMapping("/allUnAvailableSuppliers")
    public ResponseEntity<?> getAllUnAvailableProducts(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<SupplierResponseDto> suppliers = supplierService.findInactiveSuppliers(lastId,size);

        // Add null-safety check
        if (suppliers == null || suppliers.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "The list of inactive suppliers is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of inactive suppliers was obtained successfully.",
                        suppliers.size(),
                        suppliers
                )
        );
    }
}
