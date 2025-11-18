package com.api.supermercado.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public interface IssuingCompanyFullProjection {

    Integer getId();
    String getRuc();
    String getLegalName();
    String getTradeName();
    String getHeadquartersAddress();
    String getEstablishmentAddress();
    String getEstablishmentCode();
    String getEmissionPoint();
    Integer getEnvironmentType();
    Integer getEmissionType();
    Boolean getRequiresAccounting();
    String getSpecialTaxpayerNumber();
    String getNotificationEmail();
    String getCertificate();
    String getCertificatePassword();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}

