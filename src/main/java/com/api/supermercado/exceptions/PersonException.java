package com.api.supermercado.exceptions;


import lombok.Getter;

@Getter
public class PersonException extends ApiException {

    public PersonException(PersonExceptions error) {
        super(error.name(), error.getMessage());
    }

    public PersonException(PersonExceptions error, Throwable cause) {
        super(error.name(), error.getMessage(), cause);
    }
}

