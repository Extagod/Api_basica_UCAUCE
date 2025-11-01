package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductExceptions error;

    public ProductException(ProductExceptions error) {
        super(error.getMessage());
        this.error = error;
    }
}
