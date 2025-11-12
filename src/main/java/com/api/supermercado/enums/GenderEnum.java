package com.api.supermercado.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum GenderEnum {
    MALE(1),
    FEMALE(2),
    OTHER(3);
    private final int id;

    public static GenderEnum fromId(int id) {
        for (GenderEnum type : values()) {
            if (type.getId() == id) return type;
        }
        throw new IllegalArgumentException("Invalid ID type ID:" + id);
    }
}