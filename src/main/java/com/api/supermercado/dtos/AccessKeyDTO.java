package com.api.supermercado.dtos;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

public record AccessKeyDTO(
        LocalDate Date,
        String ReceiptType,
        String TaxpayerID,
        String Environment,
        String Series,
        String Sequential,
        String NumericCode,
        String IssueType) {
}
