package com.api.supermercado.dtos;

import com.api.supermercado.enums.RoleEnum;

import java.time.Instant;
import java.util.List;


public record AuthResponse(
        String message,
        String token,
        Instant tokenExpirationTime,
        List<RoleEnum> roles
) {}

