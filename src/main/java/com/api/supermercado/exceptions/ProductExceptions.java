package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum ProductExceptions {

    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "Product not found"),
    INVALID_PRODUCT_ID("INVALID_PRODUCT_ID", "The provided product ID is invalid"),
    DUPLICATE_PRODUCT("DUPLICATE_PRODUCT", "A product with the same name or code already exists"),
    INVALID_PRODUCT_DATA("INVALID_PRODUCT_DATA", "Some product fields are missing or invalid"),


    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found for the given categoryId"),
    INVALID_CATEGORY_ID("INVALID_CATEGORY_ID", "The provided category ID is invalid"),

    SUPPLIER_NOT_FOUND("SUPPLIER_NOT_FOUND", "Supplier not found for the given supplierId"),
    INVALID_SUPPLIER_ID("INVALID_SUPPLIER_ID", "The provided supplier ID is invalid"),

    STOCK_NEGATIVE("STOCK_NEGATIVE", "Stock cannot be negative"),
    PRICE_NEGATIVE("PRICE_NEGATIVE", "Price cannot be negative"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected server error occurred");

    private final String code;
    private final String message;

    ProductExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
