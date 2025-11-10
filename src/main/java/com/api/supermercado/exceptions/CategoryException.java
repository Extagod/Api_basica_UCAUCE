package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public class CategoryException extends ApiException {

    public CategoryException(CategoryExceptions error) {
        super(error.name(), error.getMessage());
    }

    public CategoryException(CategoryExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }

    public CategoryException(CategoryExceptions error, String customMessage) {
        super(error.name(), customMessage);
    }

    public CategoryException(CategoryExceptions error, String customMessage, Throwable cause) {
        super(error.name(), customMessage, cause);
    }
}




