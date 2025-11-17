package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface BranchFullResponseDto {
    Integer getIdBranch();
    String getNameBranch();
    String getAddress();
    String getPhone();
    String getEstablishmentCode();
    String getEmissionPoint();
    Boolean getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
