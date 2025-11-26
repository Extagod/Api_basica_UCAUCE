package com.api.supermercado.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReceiptTypeEnum {
    FACTURA("01"),
    NOTA_CREDITO("04"),
    NOTA_DEBITO("05");

    private final String code;


}
