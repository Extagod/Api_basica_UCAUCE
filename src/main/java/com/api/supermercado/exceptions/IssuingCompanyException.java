package com.api.supermercado.exceptions;

public class IssuingCompanyException extends ApiException {

    public IssuingCompanyException(IssuingCompanyExceptions error) {
        super(error.name(), error.getMessage());
    }

    public IssuingCompanyException(IssuingCompanyExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }

    public IssuingCompanyException(IssuingCompanyExceptions error, String customMessage) {
        super(error.name(), customMessage);
    }

    public IssuingCompanyException(IssuingCompanyExceptions error, String customMessage, Throwable cause) {
        super(error.name(), customMessage, cause);
    }

}
