package com.api.supermercado.IServices;


import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.ProductException;
import com.api.supermercado.exceptions.ProductExceptions;
import com.api.supermercado.mappers.ProductRequestMapper;
import com.api.supermercado.repositories.ProductRepository;
import com.api.supermercado.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service

public class IProduct implements ProductService {

    @Autowired
    public IProduct(ProductRequestMapper productRequestMapper, ProductRepository productRepository) {
        this.productRequestMapper = productRequestMapper;
        this.productRepository = productRepository;
    }

    private ProductRepository  productRepository;


    private ProductRequestMapper  productRequestMapper;


    @Override
    public List<ProductPageResponseDto> getProductsPaginated(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;

        return productRepository.findProductsPage(lastId, size);
    }

    @Override
    public void addProduct(ProductRequestDto productRequestDto) {
        boolean exists = productRepository.existsByBarCode(productRequestDto.getBarCode());
        if (exists) {
            throw new ProductException(ProductExceptions.DUPLICATE_PRODUCT);
        }
        Product product = productRequestMapper.toEntity(productRequestDto);
        productRepository.save(product);
    }

    @Override
    public Optional<ProductPageResponseDto> getProduct(Integer lastId, Integer size, String barCode) {

        if (barCode == null || barCode.isBlank()) {
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);
        }

        List<ProductPageResponseDto> results = productRepository.findProductsPage(lastId, size);

        return results.stream().findFirst();
    }


    @Override
    public ResponseEntity<?> deleteProduct(String barCode) {

        if (barCode == null || barCode.isBlank()) {
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);
        }

        Product product = productRepository.findByBarCode(barCode)
                .orElseThrow(() -> new ProductException(ProductExceptions.PRODUCT_NOT_FOUND));

        product.setActiveProduct(false);
        productRepository.save(product);
        return null;
    }

    @Override
    public List<ProductPageResponseDto> findProductsDisabled(Integer lastId, Integer size){
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;
        return productRepository.findProductsDisabled(lastId, size);
    }

    @Override
    public Optional<Product> UpdateProduct(String barCode, ProductRequestDto dto) {
        if (barCode == null || barCode.isBlank()) {
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);
        }

        Product existingProduct = productRepository.findByBarCode(barCode)
                .orElseThrow(() -> new ProductException(ProductExceptions.PRODUCT_NOT_FOUND));

        // Actualiza los campos desde el DTO sin reemplazar la instancia
        productRequestMapper.updateProductFromDto(dto, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);

        return Optional.of(updatedProduct);
    }



}



