package com.api.supermercado.dtos;

public record BranchRegisterDto(
        String nameBranch,
        String address,
        String phone,
        String establishmentCode,
        String emissionPoint
) {
}
