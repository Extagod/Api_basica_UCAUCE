package com.api.supermercado.exceptions;

public class InvoiceException extends ApiException{
    public InvoiceException(InvoiceExceptions error) {
        super(error.name(), error.getMessage());
    }

    public InvoiceException(InvoiceExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }

    public InvoiceException(InvoiceExceptions error, String customMessage) {
        super(error.name(), customMessage);
    }

    public InvoiceException(InvoiceExceptions error, String customMessage, Throwable cause) {
        super(error.name(), customMessage, cause);
    }

}
