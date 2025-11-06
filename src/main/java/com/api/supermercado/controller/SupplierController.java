package com.api.supermercado.controller;

import com.api.supermercado.dtos.*;
import com.api.supermercado.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<SupplierResponseDto> suppliers = supplierService.findActiveSuppliers(lastId, size);

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
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<SupplierResponseDto> suppliers = supplierService.findInactiveSuppliers(lastId, size);


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


    @PostMapping("/addSupplier")
    public ResponseEntity<?> addSupplier(@Valid @RequestBody SupplierRequestDto supplierRequestDto) {
        supplierService.createSupplier(supplierRequestDto);

        return ResponseEntity.ok(Map.of(
                "message", "Supplier successfully created"
        ));
    }

    @PutMapping("/updateSupplier")
    public ResponseEntity<?> updateSupplier(
            @Valid
            @RequestParam String taxId,
            @RequestBody SupplierRequestDto supplierRequestDto) {

        return supplierService.updateSupplier(taxId, supplierRequestDto)
                .map(updatedProduct -> ResponseEntity.ok(Map.of(
                        "message", "Product successfully updated",
                        "product", updatedProduct
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseSupplier(@Valid @RequestParam String taxId) {
        supplierService.deleteSupplier(taxId);
        return ResponseEntity.ok(Map.of(
                "message", "Supplier successfully deleted"
        ));
    }

    @GetMapping("/getSupplier")
    public ResponseEntity<?> getSupplier(
            @Valid
            @RequestParam String taxId) {

        return supplierService.getSupplier(taxId)
                .map(product ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Supplier obtained successfully.",
                                        1,
                                        product
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No Supplier found with the given barcode.",
                                        0,
                                        null
                                )
                        )
                );
    }

}
