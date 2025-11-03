package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public class CategoryException extends RuntimeException {

    private final CategoryExceptions error;

    public CategoryException(CategoryExceptions error) {
        super(error.getMessage());
        this.error = error;
    }

    public CategoryException(String customMessage, CategoryExceptions error) {
        super(customMessage);
        this.error = error;
    }
}
