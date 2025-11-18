package com.api.supermercado.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

public record IssuingCompanyRegisterDto(

        @NotBlank(message = "RUC is required")
        @Pattern(regexp = "^[0-9]{13}$", message = "RUC must contain exactly 13 numeric digits")
        String ruc,

        @NotBlank(message = "Legal name is required")
        @Size(min = 3, max = 200, message = "Legal name must be between 3 and 200 characters")
        String legalName,

        @NotBlank(message = "Trade name is required")
        @Size(min = 2, max = 200, message = "Trade name must be between 2 and 200 characters")
        String tradeName,

        @NotBlank(message = "Headquarters address is required")
        @Size(min = 10, max = 300, message = "Headquarters address must be between 10 and 300 characters")
        String headquartersAddress,

        @NotBlank(message = "Establishment address is required")
        @Size(min = 10, max = 300, message = "Establishment address must be between 10 and 300 characters")
        String establishmentAddress,

        @NotBlank(message = "Establishment code is required")
        @Pattern(regexp = "^[0-9]{3}$", message = "Establishment code must be exactly 3 digits")
        String establishmentCode,

        @NotBlank(message = "Emission point is required")
        @Pattern(regexp = "^[0-9]{3}$", message = "Emission point must be exactly 3 digits")
        String emissionPoint,

        @NotNull(message = "Environment type is required")
        @Min(value = 1, message = "Environment type must be 1 (Testing) or 2 (Production)")
        @Max(value = 2, message = "Environment type must be 1 (Testing) or 2 (Production)")
        Integer environmentType,

        @NotNull(message = "Emission type is required")
        @Min(value = 1, message = "Emission type must be 1 (Normal) or 2 (Contingency)")
        @Max(value = 2, message = "Emission type must be 1 (Normal) or 2 (Contingency)")
        Integer emissionType,

        @NotNull(message = "You must specify whether accounting is required")
        Boolean requiresAccounting,

        @Size(max = 20, message = "Special taxpayer number cannot exceed 20 characters")
        String specialTaxpayerNumber,

        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        String notificationEmail,

        String certificate,

        @Size(min = 4, max = 100, message = "Certificate password must be between 4 and 100 characters")
        String certificatePassword
) {}

