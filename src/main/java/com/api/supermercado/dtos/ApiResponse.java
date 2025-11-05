package com.api.supermercado.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")

public record ApiResponse<T>(
        String message,
        int count,
        T data,
        OffsetDateTime timestamp
) {
    public ApiResponse(String message, int count, T data) {
        this(message, count, data, OffsetDateTime.now(ZoneOffset.of("-05:00"))); // 🇪🇨 Ecuador
    }
}
