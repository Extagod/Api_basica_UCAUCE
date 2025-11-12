package com.api.supermercado.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Getter
public enum IdentificationTypeEnum {
    CEDULA_IDENTIDAD(1),
    PASAPORTE(2),
    RUC(3);

    private final int idIdentificationTypeEnum;

    public int getIdentificationTypeEnum() {
        return idIdentificationTypeEnum;
    }

    public static IdentificationTypeEnum fromId(int id) {
        for (IdentificationTypeEnum type : values()) {
            if (type.getIdentificationTypeEnum() == id) return type;
        }
        throw new IllegalArgumentException("Invalid ID type ID:" + id);
    }
}