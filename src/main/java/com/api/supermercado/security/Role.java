package com.api.supermercado.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(1),
    USER(2);

    private final int id;


    public static Role fromId(int id) {
        for (Role type : values()) {
            if (type.getId() == id) return type;
        }
        throw new IllegalArgumentException("Invalid ID type ID:" + id);
    }

}
