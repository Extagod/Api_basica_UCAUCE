package com.api.supermercado.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN(1),
    USER(2);

    private final int id;


    public static RoleEnum fromId(int id) {
        for (RoleEnum type : values()) {
            if (type.getId() == id) return type;
        }
        throw new IllegalArgumentException("Invalid ID type ID:" + id);
    }

}
