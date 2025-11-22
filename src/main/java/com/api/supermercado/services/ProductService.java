package com.api.supermercado.services;

import com.api.supermercado.dtos.ProductDetailDto;
import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Product;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductPageResponseDto> allActiveProducts(Integer lastId, Integer size);
    void addProduct(ProductRequestDto productRequestDto);
    Optional<ProductPageResponseDto> getProduct(String barCode);
    void deleteProduct(String barCode);
    List<ProductPageResponseDto> findProductsDisabled(Integer lastId, Integer size);
    Optional<Product> UpdateProduct(String barCode, ProductRequestDto dto);
    ProductDetailDto buildProductDetail(Product product, Integer quantity);
    Product findById(Integer id);

}
