package com.api.supermercado.dtos;

import java.time.LocalDateTime;

public interface IssuingCompanyProjection {

    Integer getId();
    String getRuc();
    String getLegalName();
    String getTradeName();
    String getEstablishmentCode();
    String getEmissionPoint();
    Boolean getRequiresAccounting();
    String getSpecialTaxpayerNumber();
    String getNotificationEmail();
    LocalDateTime getCreatedAt();
}