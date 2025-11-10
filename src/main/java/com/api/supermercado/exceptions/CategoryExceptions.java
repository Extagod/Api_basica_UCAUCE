package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum CategoryExceptions {

    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found"),
    INVALID_CATEGORY_ID("INVALID_CATEGORY_ID", "The provided category ID is invalid"),
    DUPLICATE_CATEGORY("DUPLICATE_CATEGORY", "A category with the same name already exists"),
    INVALID_CATEGORY_DATA("INVALID_CATEGORY_DATA", "Some category fields are missing or invalid"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected server error occurred");

    private final String code;
    private final String message;

    CategoryExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
