package com.api.supermercado.controller;

import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(productService.getProductsPaginated(lastId, size));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProduct(
            @RequestParam(required = false) Integer lastId,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = true) String barCode) {

        int last = (lastId == null) ? 0 : lastId;
        int pageSize = (size == null) ? 10 : size;

        return productService.getProduct(last, pageSize, barCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
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

    @GetMapping("/unavailableProducts")
    public ResponseEntity<?> getAvailableProducts(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(productService.findProductsDisabled(lastId, size));
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
