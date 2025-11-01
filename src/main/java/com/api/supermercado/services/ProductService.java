package com.api.supermercado.services;

import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Product;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //Traer todos los productos en base a la psaginacion
    List<ProductPageResponseDto> getProductsPaginated(Integer lastId, Integer size);
    void addProduct(ProductRequestDto productRequestDto);
    Optional<ProductPageResponseDto> getProduct(Integer lastId, Integer size, String barCode);
    ResponseEntity<?> deleteProduct(String barCode);
    List<ProductPageResponseDto> findProductsDisabled(Integer lastId, Integer size);
    Optional<Product> UpdateProduct(String barCode, ProductRequestDto dto);
}
