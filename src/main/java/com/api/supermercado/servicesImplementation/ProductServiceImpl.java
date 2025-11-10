package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.ProductException;
import com.api.supermercado.exceptions.ProductExceptions;
import com.api.supermercado.mappers.ProductRequestMapper;
import com.api.supermercado.repositories.ProductRepository;
import com.api.supermercado.services.ProductService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRequestMapper productRequestMapper;

    public ProductServiceImpl(ProductRequestMapper productRequestMapper, ProductRepository productRepository) {
        this.productRequestMapper = productRequestMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductPageResponseDto> allActiveProducts(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;
        return productRepository.allActiveProducts(lastId, size);
    }

    @Override
    public void addProduct(ProductRequestDto dto) {
        try {
            if (productRepository.existsByBarCode(dto.barCode())) {
                throw new ProductException(ProductExceptions.DUPLICATE_PRODUCT);
            }

            Product product = productRequestMapper.toEntity(dto);
            productRepository.save(product);

        } catch (DataAccessException e) {
            throw new ProductException(ProductExceptions.DATABASE_ERROR, e);
        }
    }

    @Override
    public Optional<ProductPageResponseDto> getProduct(String barCode) {
        if (barCode == null || barCode.isBlank())
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);

        return productRepository.findProductsPagebyBarcode(barCode)
                .stream()
                .findFirst();
    }

    @Override
    public void deleteProduct(String barCode) {


        if(barCode == null || barCode.isBlank()){
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);
        }

        Product product = productRepository.findByBarCode(barCode)
                .orElseThrow(() -> new ProductException(ProductExceptions.PRODUCT_NOT_FOUND));

        product.setActiveProduct(false);
        productRepository.save(product);
    }

    @Override
    public List<ProductPageResponseDto> findProductsDisabled(Integer lastId, Integer size) {
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

        if (!existingProduct.getBarCode().equalsIgnoreCase(dto.barCode())
                && productRepository.existsByBarCode(dto.barCode())) {
            throw new ProductException(ProductExceptions.DUPLICATE_PRODUCT);
        }

        productRequestMapper.updateProductFromDto(dto, existingProduct);

        Product updated = productRepository.save(existingProduct);

        return Optional.of(updated);
    }



}


