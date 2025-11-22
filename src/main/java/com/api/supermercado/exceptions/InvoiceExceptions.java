package com.api.supermercado.exceptions;

import lombok.Getter;

@Getter
public enum InvoiceExceptions {

    // ------------------------- NOT FOUND -------------------------
    INVOICE_NOT_FOUND("INVOICE_NOT_FOUND", "Invoice not found"),
    INVOICE_DETAILS_NOT_FOUND("INVOICE_DETAILS_NOT_FOUND", "No invoice details found for this invoice"),
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND", "Customer associated with invoice not found"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "A product in the invoice details was not found"),

    // ------------------------- INVALID DATA -------------------------
    INVALID_DATA("INVALID_DATA", "The invoice contains invalid or incomplete data"),
    INVALID_ISSUE_DATE("INVALID_ISSUE_DATE", "Issue date cannot be in the future"),
    EMPTY_INVOICE_DETAIL("EMPTY_INVOICE_DETAIL", "The invoice must contain at least one product detail"),

    // FOREIGN KEYS
    INVALID_ISSUING_COMPANY_ID("INVALID_ISSUING_COMPANY_ID", "Invalid issuing company ID"),
    INVALID_CUSTOMER_ID("INVALID_CUSTOMER_ID", "Invalid customer ID"),
    INVALID_CREATOR_USER_ID("INVALID_CREATOR_USER_ID", "Invalid creator user ID"),

    // ACCESS KEY / SEQUENTIAL / STATUS
    DUPLICATE_ACCESS_KEY("DUPLICATE_ACCESS_KEY", "An invoice with this access key already exists"),
    INVALID_ACCESS_KEY_FORMAT("INVALID_ACCESS_KEY_FORMAT", "The access key format is invalid"),
    INVALID_SEQUENTIAL_FORMAT("INVALID_SEQUENTIAL_FORMAT", "The sequential number format is invalid"),
    INVALID_STATUS("INVALID_STATUS", "The invoice status provided is invalid"),

    // TOTALS
    INVALID_TOTAL_VALUES("INVALID_TOTAL_VALUES", "The totals are inconsistent or invalid"),
    NEGATIVE_AMOUNT("NEGATIVE_AMOUNT", "Amount values cannot be negative"),
    TOTAL_CALCULATION_ERROR("TOTAL_CALCULATION_ERROR", "Error calculating invoice totals"),

    // DOCUMENTS
    INVALID_XML("INVALID_XML", "The XML document provided is invalid"),
    INVALID_XML_SIGNATURE("INVALID_XML_SIGNATURE", "The signed XML document is invalid"),
    INVALID_RIDE_PDF("INVALID_RIDE_PDF", "The RIDE PDF is invalid or corrupted"),

    // SRI AUTHORIZATION
    SRI_ERROR("SRI_ERROR", "The SRI returned an error when processing the invoice"),
    SRI_AUTHORIZATION_FAILED("SRI_AUTHORIZATION_FAILED", "The SRI did not authorize the invoice"),
    INVALID_SRI_STATUS("INVALID_SRI_STATUS", "Invalid SRI status provided"),

    // ------------------------- BUILDER ERRORS (NEW) -------------------------
    PRODUCT_DETAIL_BUILD_ERROR("PRODUCT_DETAIL_BUILD_ERROR", "Error building product detail entry"),
    INVOICE_REQUEST_BUILD_ERROR("INVOICE_REQUEST_BUILD_ERROR", "Error building the full invoice request for SRI"),

    // ------------------------- DATABASE ERRORS -------------------------
    DATABASE_ERROR("DATABASE_ERROR", "An unexpected database error occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Unexpected internal server error occurred");

    private final String code;
    private final String message;

    InvoiceExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
