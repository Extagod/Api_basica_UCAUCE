package com.api.supermercado.repositories;

import com.api.supermercado.dtos.SupplierResponseDto;
import com.api.supermercado.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value = """

            SELECT 
            supplier_id AS supplierId,
            company_name AS companyName,
            address AS address,
            phone AS phone,
            email AS email,
            is_active AS isActive,
            created_at AS createdAt,
            updated_at AS updatedAt
        FROM supplier
        WHERE is_active = true
          AND supplier_id > :lastId
        ORDER BY supplier_id ASC
        LIMIT :size
        """,
            nativeQuery = true)
    List<SupplierResponseDto> findActiveSuppliers(@Param("lastId") Integer lastId, @Param("size") Integer size);


    @Query(value = """

            SELECT 
            supplier_id AS supplierId,
            company_name AS companyName,
            address AS address,
            phone AS phone,
            email AS email,
            is_active AS isActive,
            created_at AS createdAt,
            updated_at AS updatedAt
        FROM supplier
        WHERE is_active = false
          AND supplier_id > :lastId
        ORDER BY supplier_id ASC
        LIMIT :size
        """,
            nativeQuery = true)
    List<SupplierResponseDto> findInActiveSuppliers(@Param("lastId") Integer lastId, @Param("size") Integer size);

}