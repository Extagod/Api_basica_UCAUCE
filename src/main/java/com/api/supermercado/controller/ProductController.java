package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allAvailableProducts")
    public ResponseEntity<?> getAllAvailableProducts(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<ProductPageResponseDto> products = productService.allActiveProducts(lastId, size);

        if (products.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "The list of active products is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of active products was obtained successfully.",
                        products.size(),
                        products
                )
        );
    }

    @GetMapping("/searchByBarCode")
    public ResponseEntity<?> getProduct(
            @Valid
            @RequestParam String barCode) {

        return productService.getProduct(barCode)
                .map(product ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Product obtained successfully.",
                                        1,
                                        product
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No product found with the given barcode.",
                                        0,
                                        null
                                )
                        )
                );
    }

    @PostMapping("/Add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
        return ResponseEntity.ok(Map.of(
                "message", "Product successfully created"
        ));
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseProduct(@Valid @RequestParam String barCode) {
        productService.deleteProduct(barCode);
        return ResponseEntity.ok(Map.of(
                "message", "Product successfully deleted"
        ));
    }

    @GetMapping("/unavailableProducts")
    public ResponseEntity<?> getUnavailableProducts(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<ProductPageResponseDto> products = productService.findProductsDisabled(lastId, size);

        if (products.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            "There are no inactive products registered.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Inactive products obtained successfully.",
                        products.size(),
                        products
                )
        );
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(
            @Valid
            @RequestParam String barCode,
            @RequestBody ProductRequestDto productRequestDto) {

        return productService.UpdateProduct(barCode, productRequestDto)
                .map(updatedProduct -> ResponseEntity.ok(Map.of(
                        "message", "Product successfully updated",
                        "product", updatedProduct
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
