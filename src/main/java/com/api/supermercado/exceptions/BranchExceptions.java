package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum BranchExceptions {

    BRANCH_NOT_FOUND("BRANCH_NOT_FOUND", "Branch not found"),
    INVALID_BRANCH_DATA("INVALID_BRANCH_DATA", "Invalid or incomplete branch data"),

    DUPLICATE_BRANCH_CODE("DUPLICATE_BRANCH_CODE", "A branch with this branchCode already exists"),
    DUPLICATE_BRANCH_NAME("DUPLICATE_BRANCH_NAME", "A branch with this name already exists"),

    INVALID_ESTABLISHMENT_CODE("INVALID_ESTABLISHMENT_CODE", "The provided establishment code is invalid"),
    DUPLICATE_ESTABLISHMENT_CODE("DUPLICATE_ESTABLISHMENT_CODE", "A branch with this establishment code already exists"),
    INVALID_EMISSION_POINT("INVALID_EMISSION_POINT", "The provided emission point is invalid"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected internal server error occurred");

    private final String code;
    private final String message;

    BranchExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
