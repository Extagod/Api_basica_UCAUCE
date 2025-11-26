package com.api.supermercado.dtos;

import com.api.supermercado.enums.ReceiptTypeEnum;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

public record AccessKeyDTO(
        ReceiptTypeEnum documentType,
        String ruc,              // RUC del emisor
        String environment,      // 1 prod | 2 pruebas
        String sequential,       // 000000123
        String numericCode,      // valor aleatorio
        String emissionType      // 1 normal
) {
}
