package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allAvailableProducts")
    public ResponseEntity<?> getAllAvailableProducts(
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

    // ✅ Search by barcode with validation and standardized response
    @GetMapping("/searchByBarCode")
    public ResponseEntity<?> getProduct(

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
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
        return ResponseEntity.ok(Map.of(
                "message", "Product successfully created"
        ));
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseProduct(@RequestParam String barCode) {
        productService.deleteProduct(barCode);
        return ResponseEntity.ok(Map.of(
                "message", "Product successfully deleted"
        ));
    }

    // ✅ Get unavailable products with validation and ApiResponse
    @GetMapping("/unavailableProducts")
    public ResponseEntity<?> getUnavailableProducts(
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

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
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
