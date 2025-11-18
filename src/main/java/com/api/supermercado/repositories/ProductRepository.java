package com.api.supermercado.repositories;

import com.api.supermercado.dtos.ProductPageResponseDto;
import com.api.supermercado.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = """
        SELECT 
            p.product_id AS productId,
            p.has_tax AS hasTax,
            p.name AS productName,
            p.description AS productDescription,
            p.active_product AS activeProduct,
            p.price AS price,
            p.stock AS stock,
            p.bar_code AS barCode,
            c.category_id AS categoryId,
            c.name_category AS categoryName,
            s.supplier_id AS supplierId,
            s.company_name AS supplierName,
            s.created_at AS created_at,
            s.updated_at AS updated_at
        FROM product p
        JOIN category c 
            ON p.category_id = c.category_id
        JOIN supplier s 
            ON p.supplier_id = s.supplier_id
        WHERE p.product_id > :lastProductId
          AND p.active_product = true
        ORDER BY p.product_id
        LIMIT :pageSize
        """,
            nativeQuery = true)
    List<ProductPageResponseDto> allActiveProducts(
            @Param("lastProductId") Integer lastProductId,
            @Param("pageSize") Integer pageSize
    );


    boolean existsByBarCode(String barCode);

    @Query(value = """
    SELECT 
        p.product_id AS productId,
        p.name AS productName,
        p.has_tax AS hasTax,
        p.description AS productDescription,
        p.active_product AS activeProduct,
        p.price AS price,
        p.stock AS stock,
        p.bar_code AS barCode,
        c.category_id AS categoryId,
        c.name_category AS categoryName,
        s.supplier_id AS supplierId,
        s.company_name AS supplierName,
        s.created_at AS created_at,
        s.updated_at AS updated_at
    FROM product p
    JOIN category c 
        ON p.category_id = c.category_id
    JOIN supplier s 
        ON p.supplier_id = s.supplier_id
    WHERE 
        (:barCode IS NULL OR p.bar_code = :barCode)
        AND p.product_id > :lastProductId
    ORDER BY p.product_id
    LIMIT :pageSize
    """,
            nativeQuery = true)
    Optional<ProductPageResponseDto> findProductsPagebyBarcode(
            @Param("barCode") String barCode
    );

    Optional<Product> findByBarCode(String barCode);


    @Query(value = """
    SELECT 
        p.product_id AS productId,
        p.name AS productName,
        p.has_tax AS hasTax,
        p.description AS productDescription,
        p.active_product AS activeProduct,
        p.price AS price,
        p.stock AS stock,
        p.bar_code AS barCode,
        c.category_id AS categoryId,
        c.name_category AS categoryName,
        s.supplier_id AS supplierId,
        s.company_name AS supplierName,
        s.created_at AS created_at,
        s.updated_at AS updated_at
    FROM product p
    JOIN category c 
        ON p.category_id = c.category_id
    JOIN supplier s 
        ON p.supplier_id = s.supplier_id
    WHERE p.product_id > :lastProductId
      AND p.active_product = false
    ORDER BY p.product_id
    LIMIT :pageSize
    """,
            nativeQuery = true)
    List<ProductPageResponseDto> findProductsDisabled(
            @Param("lastProductId") Integer lastProductId,
            @Param("pageSize") Integer pageSize
    );


}

