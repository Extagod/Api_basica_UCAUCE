package com.api.supermercado.repositories;

import com.api.supermercado.dtos.BranchFullResponseDto;
import com.api.supermercado.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query(value = """
        SELECT
            b.branch_id AS idBranch,
            b.name AS nameBranch,
            b.address AS address,
            b.phone AS phone,
            b.establishment_code AS establishmentCode,
            b.emission_point AS emissionPoint,
            b.is_active AS isActive,
            b.created_at AS createdAt,
            b.updated_at AS updatedAt
        FROM branch b
        WHERE b.branch_id > :lastId
          AND b.is_active = TRUE
        ORDER BY b.branch_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<BranchFullResponseDto> findActiveBranches(
            @Param("lastId") Integer lastId,
            @Param("pageSize") Integer pageSize
    );


    @Query(value = """
        SELECT
            b.branch_id AS idBranch,
            b.name AS nameBranch,
            b.address AS address,
            b.phone AS phone,
            b.establishment_code AS establishmentCode,
            b.emission_point AS emissionPoint,
            b.is_active AS isActive,
            b.created_at AS createdAt,
            b.updated_at AS updatedAt
        FROM branch b
        WHERE b.branch_id > :lastId
          AND b.is_active = FALSE
        ORDER BY b.branch_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<BranchFullResponseDto> findInactiveBranches(
            @Param("lastId") Integer lastId,
            @Param("pageSize") Integer pageSize
    );



    Optional<Branch> findByEstablishmentCode(String establishmentCode);

    boolean existsBranchByEstablishmentCode(String establishmentCode);
}
