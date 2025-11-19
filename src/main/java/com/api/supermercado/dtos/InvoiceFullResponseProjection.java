package com.api.supermercado.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public interface InvoiceFullResponseProjection {

    Integer getId();

    // FK IDs
    Integer getIssuingCompanyId();
    Integer getCustomerId();
    Integer getCreatorUserId();

    // Datos principales
    Instant getIssueDate();
    String getAccessKey();
    String getSequential();
    String getStatus();

    // Totales
    BigDecimal getSubtotalWithoutTax();
    BigDecimal getTotalVat();
    BigDecimal getTotalWithTax();

    // Documentos
    String getXmlDocument();
    String getXmlSigned();
    byte[] getRidePdf();

    // Datos SRI
    String getAuthorizationNumber();
    Instant getAuthorizationDate();
    String getSriStatus();
    String getSriMessages();
    Instant getSriSentDate();
    Instant getSriResponseDate();
    String getSriAuthorizationNumber();
    Instant getSriAuthorizationDate();

    // Otros
    String getOriginalData();
    Boolean getIsRegisteredInCashRegister();
}
