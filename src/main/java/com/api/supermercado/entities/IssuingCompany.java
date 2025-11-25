package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "issuing_company",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"ruc"})
        }
)
@EntityListeners(AuditingEntityListener.class)
public class IssuingCompany {

    // ===================== ID =====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issuing_company_id", nullable = false)
    private Integer idIssuingCompany;

    // ===================== RUC =====================
    @NotBlank(message = "RUC is required")
    @Pattern(regexp = "^[0-9]{13}$", message = "RUC must contain exactly 13 numeric digits")
    @Column(name = "ruc", nullable = false, length = 13, unique = true)
    private String ruc;

    // ===================== LEGAL NAME =====================
    @NotBlank(message = "Legal name is required")
    @Size(min = 3, max = 200, message = "Legal name must be between 3 and 200 characters")
    @Column(name = "legal_name", nullable = false, length = 200)
    private String legalName;

    // ===================== TRADE NAME =====================
    @NotBlank(message = "Trade name is required")
    @Size(min = 2, max = 200, message = "Trade name must be between 2 and 200 characters")
    @Column(name = "trade_name", nullable = false, length = 200)
    private String tradeName;

    // ===================== HEADQUARTERS ADDRESS =====================
    @NotBlank(message = "Headquarters address is required")
    @Size(min = 10, max = 300, message = "Headquarters address must be between 10 and 300 characters")
    @Column(name = "headquarters_address", nullable = false, length = 300)
    private String headquartersAddress;

    // ===================== ESTABLISHMENT ADDRESS =====================
    @NotBlank(message = "Establishment address is required")
    @Size(min = 10, max = 300, message = "Establishment address must be between 10 and 300 characters")
    @Column(name = "establishment_address", nullable = false, length = 300)
    private String establishmentAddress;

    // ===================== ESTABLISHMENT CODE (001–999) =====================
    @NotBlank(message = "Establishment code is required")
    @Pattern(regexp = "^[0-9]{3}$", message = "Establishment code must be exactly 3 digits")
    @Column(name = "establishment_code", nullable = false, length = 3)
    private String establishmentCode;

    // ===================== EMISSION POINT (001–999) =====================
    @NotBlank(message = "Emission point is required")
    @Pattern(regexp = "^[0-9]{3}$", message = "Emission point must be exactly 3 digits")
    @Column(name = "emission_point", nullable = false, length = 3)
    private String emissionPoint;

    // ===================== SERIES (establishment + emission point) =====================
    @NotBlank(message = "Series is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Series must contain exactly 6 digits")
    @Column(name = "series", nullable = false, length = 6)
    private String series;

    // ===================== ENVIRONMENT TYPE =====================
    @NotNull(message = "Environment type is required")
    @Min(value = 1, message = "Environment type must be 1 (Testing) or 2 (Production)")
    @Max(value = 2, message = "Environment type must be 1 (Testing) or 2 (Production)")
    @Column(name = "environment_type", nullable = false)
    private Integer environmentType;

    // ===================== EMISSION TYPE =====================
    @NotNull(message = "Emission type is required")
    @Min(value = 1, message = "Emission type must be 1 (Normal) or 2 (Contingency)")
    @Max(value = 2, message = "Emission type must be 1 (Normal) or 2 (Contingency)")
    @Column(name = "emission_type", nullable = false)
    private Integer emissionType;

    // ===================== KEEP ACCOUNTING =====================
    @NotNull(message = "Required accounting must be specified")
    @Column(name = "requires_accounting", nullable = false)
    private Boolean requiresAccounting;

    // ===================== SPECIAL TAXPAYER =====================
    @Size(max = 20, message = "Special taxpayer number cannot exceed 20 characters")
    @Column(name = "special_taxpayer_number", length = 20)
    private String specialTaxpayerNumber;

    // ===================== NOTIFICATION EMAIL =====================
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(name = "notification_email", length = 100)
    private String notificationEmail;

    // ===================== DIGITAL CERTIFICATE =====================
    @Column(name = "certificate", length = 500)
    private String certificate;

    // ===================== CERTIFICATE PASSWORD =====================
    @Size(min = 4, max = 100, message = "Certificate password must be between 4 and 100 characters")
    @Column(name = "certificate_password", length = 100)
    private String certificatePassword;

    // ===================== AUDITING =====================
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
