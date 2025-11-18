package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum IssuingCompanyExceptions {

    COMPANY_NOT_FOUND("COMPANY_NOT_FOUND", "Issuing company not found"),
    INVALID_DATA("INVALID_DATA", "Invalid or incomplete issuing company data"),

    DUPLICATE_RUC("DUPLICATE_RUC", "An issuing company with this RUC already exists"),
    DUPLICATE_TRADE_NAME("DUPLICATE_TRADE_NAME", "An issuing company with this trade name already exists"),
    DUPLICATE_ESTABLISHMENT_CODE("DUPLICATE_ESTABLISHMENT_CODE", "An issuing company with this establishment code already exists"),

    INVALID_RUC("INVALID_RUC", "The provided RUC is invalid"),
    INVALID_ESTABLISHMENT_CODE("INVALID_ESTABLISHMENT_CODE", "The establishment code must be 3 numeric digits"),
    INVALID_EMISSION_POINT("INVALID_EMISSION_POINT", "The emission point must be 3 numeric digits"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected internal server error occurred");

    private final String code;
    private final String message;

    IssuingCompanyExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
