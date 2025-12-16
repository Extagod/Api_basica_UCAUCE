package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum PersonExceptions {

    PERSON_NOT_FOUND("PERSON_NOT_FOUND", "Person not found"),
    INVALID_PERSON_ID("INVALID_PERSON_ID", "The provided person ID is invalid"),
    DUPLICATE_IDENTIFICATION("DUPLICATE_IDENTIFICATION", "A person with the same identification number already exists"),
    INVALID_PERSON_DATA("INVALID_PERSON_DATA", "Some person fields are missing or invalid"),
    DUPLICATE_PERSON_USERNAME("DUPLICATE_PERSON_USERNAME", "A person with the same username already exists"),
    DUPLICATE_PERSON_EMAIL("DUPLICATE_PERSON_EMAIL", "A person with the same email already exists"),
    IDENTIFICATION_TYPE_NOT_FOUND("IDENTIFICATION_TYPE_NOT_FOUND", "Identification type not found for the given identificationTypeId"),
    INVALID_IDENTIFICATION_TYPE_ID("INVALID_IDENTIFICATION_TYPE_ID", "The provided identification type ID is invalid"),
    THE_USER_IS_NOT_AN_ADMIN("THE USER IS NOT AN ADMIN", "The user needs to be an administrator to obtain the JWT token"),




    GENDER_NOT_FOUND("GENDER_NOT_FOUND", "Gender not found for the given genderId"),
    INVALID_GENDER_ID("INVALID_GENDER_ID", "The provided gender ID is invalid"),

    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected server error occurred"),
    INVALID_JWT_TOKEN("INVALID_JWT_TOKEN", "Invalid JWT token");

    private final String code;
    private final String message;

    PersonExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}