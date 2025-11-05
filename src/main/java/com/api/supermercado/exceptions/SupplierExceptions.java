package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum SupplierExceptions {

    SUPPLIER_NOT_FOUND("SUPPLIER_NOT_FOUND", "Supplier not found"),
    INVALID_SUPPLIER_ID("INVALID_SUPPLIER_ID", "The provided supplier ID is invalid"),

    DUPLICATE_TAX_ID("DUPLICATE_TAX_ID", "A supplier with the same tax ID already exists"),
    INVALID_SUPPLIER_DATA("INVALID_SUPPLIER_DATA", "Some supplier fields are missing or invalid"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred while processing the supplier"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected server error occurred");

    private final String code;
    private final String message;

    SupplierExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
