package com.api.supermercado.repositories;

import com.api.supermercado.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {

    // 1️⃣ Obtener todos los detalles de una factura
    @Query(value = """
        SELECT *
        FROM invoice_detail
        WHERE invoice_id = :invoiceId
        ORDER BY invoice_detail_id
    """, nativeQuery = true)
    List<InvoiceDetail> findByInvoiceId(@Param("invoiceId") Integer invoiceId);


    // 2️⃣ (Opcional pero recomendado) Obtener el subtotal total de la factura
    @Query(value = """
        SELECT COALESCE(SUM(subtotal), 0)
        FROM invoice_detail
        WHERE invoice_id = :invoiceId
    """, nativeQuery = true)
    BigDecimal sumSubtotalByInvoiceId(@Param("invoiceId") Integer invoiceId);


    // 3️⃣ (Opcional) Obtener el total del IVA de la factura
    @Query(value = """
        SELECT COALESCE(SUM(tax_value), 0)
        FROM invoice_detail
        WHERE invoice_id = :invoiceId
    """, nativeQuery = true)
    BigDecimal sumTaxByInvoiceId(@Param("invoiceId") Integer invoiceId);


    // 4️⃣ (Opcional) Listar solo product_id y quantity si quieres performance
    @Query(value = """
        SELECT product_id, quantity
        FROM invoice_detail
        WHERE invoice_id = :invoiceId
    """, nativeQuery = true)
    List<Object[]> findProductsAndQuantities(@Param("invoiceId") Integer invoiceId);

}
