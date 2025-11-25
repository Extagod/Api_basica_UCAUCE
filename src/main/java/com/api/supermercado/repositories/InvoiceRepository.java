package com.api.supermercado.repositories;

import com.api.supermercado.dtos.InvoiceFullResponseProjection;
import com.api.supermercado.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query(value = """
    SELECT sequential 
    FROM invoice 
    WHERE issuing_company_id = :companyId
    ORDER BY invoice_id DESC
    LIMIT 1
""", nativeQuery = true)
    String findLastSequentialByCompanyId(@Param("companyId") Integer companyId);


    @Query(value = """
    SELECT
        i.invoice_id AS id,
        i.issuing_company_id AS issuingCompanyId, 
        i.sequential AS sequential
    FROM invoice i
    WHERE i.issuing_company_id = :companyId
      AND i.sri_status = :status
    ORDER BY i.invoice_id DESC
    LIMIT 1
""", nativeQuery = true)
    Optional<Invoice> findLastInvoiceByStatus(
            @Param("companyId") Integer companyId,
            @Param("status") String status
    );




    @Query(value = """
    SELECT
        i.invoice_id AS id,
        i.issuing_company_id AS issuingCompanyId,
        i.customer_id AS customerId,
        i.employee_id AS employeeId,
        i.issue_date AS issueDate,
        i.access_key AS accessKey,
        i.sequential AS sequential,
        i.status AS status,
        i.subtotal_without_tax AS subtotalWithoutTax,
        i.total_vat AS totalVat,
        i.total_with_tax AS totalWithTax,
        i.xml_document AS xmlDocument,
        i.xml_signed AS xmlSigned,
        i.ride_pdf AS ridePdf,
        i.authorization_number AS authorizationNumber,
        i.authorization_date AS authorizationDate,
        i.sri_status AS sriStatus,
        i.sri_messages AS sriMessages,
        i.sri_sent_date AS sriSentDate,
        i.sri_response_date AS sriResponseDate,
        i.sri_authorization_number AS sriAuthorizationNumber,
        i.sri_authorization_date AS sriAuthorizationDate,
        i.original_data AS originalData,
        i.is_registered_in_cash_register AS isRegisteredInCashRegister
    FROM invoice i
    WHERE i.invoice_id = :invoiceId
    LIMIT 1
""", nativeQuery = true)
    InvoiceFullResponseProjection findByIdProjection(@Param("invoiceId") Integer invoiceId);

    @Query(value = """
    SELECT
        i.invoice_id AS id,
        i.issuing_company_id AS issuingCompanyId,
        i.customer_id AS customerId,
        i.employee_id AS employeeId,
        i.issue_date AS issueDate,
        i.access_key AS accessKey,
        i.sequential AS sequential,
        i.status AS status,
        i.subtotal_without_tax AS subtotalWithoutTax,
        i.total_vat AS totalVat,
        i.total_with_tax AS totalWithTax,
        i.xml_document AS xmlDocument,
        i.xml_signed AS xmlSigned,
        i.ride_pdf AS ridePdf,
        i.authorization_number AS authorizationNumber,
        i.authorization_date AS authorizationDate,
        i.sri_status AS sriStatus,
        i.sri_messages AS sriMessages,
        i.sri_sent_date AS sriSentDate,
        i.sri_response_date AS sriResponseDate,
        i.sri_authorization_number AS sriAuthorizationNumber,
        i.sri_authorization_date AS sriAuthorizationDate,
        i.original_data AS originalData,
        i.is_registered_in_cash_register AS isRegisteredInCashRegister
    FROM invoice i
    WHERE i.issuing_company_id = :companyId
      AND i.sri_status = :status
    ORDER BY i.invoice_id DESC
    LIMIT 1
""", nativeQuery = true)
    Optional<Invoice> findLastAuthorizedInvoice(
            @Param("issuingCompanyId") Integer issuingCompanyId,
            @Param("status") String status
    );



}
