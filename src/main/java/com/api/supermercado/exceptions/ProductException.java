package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public class ProductException extends ApiException {

    public ProductException(ProductExceptions error) {
        super(error.name(), error.getMessage());
    }

    public ProductException(ProductExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }
}

