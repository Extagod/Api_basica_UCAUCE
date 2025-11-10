package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String code;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}

