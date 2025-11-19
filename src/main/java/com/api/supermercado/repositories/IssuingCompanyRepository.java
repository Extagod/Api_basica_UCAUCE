package com.api.supermercado.repositories;

import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.dtos.IssuingCompanyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssuingCompanyRepository extends JpaRepository<IssuingCompany, Integer> {

    // ---------------- ACTIVE ISSUING COMPANIES ----------------
    @Query(value = """
        SELECT
            ic.issuing_company_id   AS id,
            ic.ruc                  AS ruc,
            ic.legal_name           AS legalName,
            ic.trade_name           AS tradeName,
            ic.establishment_code   AS establishmentCode,
            ic.emission_point       AS emissionPoint,
            ic.requires_accounting  AS requiresAccounting,
            ic.special_taxpayer_number AS specialTaxpayerNumber,
            ic.notification_email   AS notificationEmail,
            ic.created_at           AS createdAt,
            ic.updated_at           AS updatedAt
        FROM issuing_company ic
        WHERE ic.issuing_company_id > :lastId
          AND ic.requires_accounting = TRUE
        ORDER BY ic.issuing_company_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<IssuingCompanyProjection> findActiveIssuingCompanies(
            @Param("lastId") Integer lastId,
            @Param("pageSize") Integer pageSize
    );

    // ---------------- INACTIVE ISSUING COMPANIES ----------------
    @Query(value = """
        SELECT
            ic.issuing_company_id   AS id,
            ic.ruc                  AS ruc,
            ic.legal_name           AS legalName,
            ic.trade_name           AS tradeName,
            ic.establishment_code   AS establishmentCode,
            ic.emission_point       AS emissionPoint,
            ic.requires_accounting  AS requiresAccounting,
            ic.special_taxpayer_number AS specialTaxpayerNumber,
            ic.notification_email   AS notificationEmail,
            ic.created_at           AS createdAt,
            ic.updated_at           AS updatedAt
        FROM issuing_company ic
        WHERE ic.issuing_company_id > :lastId
          AND ic.requires_accounting = FALSE
        ORDER BY ic.issuing_company_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<IssuingCompanyProjection> findInactiveIssuingCompanies(
            @Param("lastId") Integer lastId,
            @Param("pageSize") Integer pageSize
    );

    // ---------------- FIND BY ESTABLISHMENT CODE (ENTITY) ----------------
    Optional<IssuingCompany> findByEstablishmentCode(String establishmentCode);

    // ---------------- EXISTS BY ESTABLISHMENT CODE ----------------
    boolean existsByEstablishmentCode(String establishmentCode);

    // ---------------- FIND BY RUC (ENTITY) ----------------
    Optional<IssuingCompany> findByRuc(String ruc);

    // ---------------- FIND PROJECTION BY ESTABLISHMENT CODE ----------------
    @Query(value = """
        SELECT
            ic.issuing_company_id   AS id,
            ic.ruc                  AS ruc,
            ic.legal_name           AS legalName,
            ic.trade_name           AS tradeName,
            ic.establishment_code   AS establishmentCode,
            ic.emission_point       AS emissionPoint,
            ic.requires_accounting  AS requiresAccounting,
            ic.special_taxpayer_number AS specialTaxpayerNumber,
            ic.notification_email   AS notificationEmail,
            ic.created_at           AS createdAt,
            ic.updated_at           AS updatedAt
        FROM issuing_company ic
        WHERE ic.establishment_code = :establishmentCode
        LIMIT 1
        """, nativeQuery = true)
    Optional<IssuingCompanyProjection> findProjectionByEstablishmentCode(
            @Param("establishmentCode") String establishmentCode
    );

    Optional<IssuingCompany> findById(Integer id);
}
