package com.api.supermercado.exceptions;

public class BranchException extends ApiException {

    public BranchException(BranchExceptions error) {
        super(error.name(), error.getMessage());
    }

    public BranchException(BranchExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }

    public BranchException(BranchExceptions error, String customMessage) {
        super(error.name(), customMessage);
    }

    public BranchException(BranchExceptions error, String customMessage, Throwable cause) {
        super(error.name(), customMessage, cause);
    }

}
