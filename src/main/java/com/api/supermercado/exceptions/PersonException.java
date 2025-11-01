package com.api.supermercado.exceptions;


import lombok.Getter;

@Getter
public class PersonException extends RuntimeException {

    private final PersonExceptions error;

    public PersonException(PersonExceptions error) {
        super(error.getMessage());
        this.error = error;
    }

}