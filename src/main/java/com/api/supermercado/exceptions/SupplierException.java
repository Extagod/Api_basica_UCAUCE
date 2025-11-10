package com.api.supermercado.exceptions;

public class SupplierException extends ApiException{

    public SupplierException(SupplierExceptions error) {
        super(error.name(), error.getMessage());
    }
    public SupplierException(ProductExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }
}
