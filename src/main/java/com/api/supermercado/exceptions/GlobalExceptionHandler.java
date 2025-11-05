package com.api.supermercado.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {

        Map<String, Object> body = Map.of(
                "error", ex.getCode(),
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        );

        HttpStatus status = switch (ex.getCode()) {
            case "CATEGORY_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "INVALID_CATEGORY_DATA" -> HttpStatus.BAD_REQUEST;

            case "PRODUCT_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "DUPLICATE_PRODUCT" -> HttpStatus.CONFLICT;

            case "PERSON_NOT_FOUND" -> HttpStatus.NOT_FOUND;

            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return ResponseEntity.status(status).body(body);
    }
}
