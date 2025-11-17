package com.api.supermercado.repositories;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByUsername(String username);

    Optional<Employee> findByIdentificationNumber(String identificationNumber);

    // -------------------------------------------------------------------------
    // 1. LISTAR EMPLOYEES ACTIVOS (DTO GENERAL)
    // -------------------------------------------------------------------------
    @Query(value = """
        SELECT 
            p.person_id AS id,
            it.identification_type_id AS identificationTypeId,
            it.description AS identificationTypeDescription,
            p.identification_number AS identificationNumber,
            p.first_name AS firstName,
            p.last_name AS lastName,
            g.gender_id AS genderId,
            g.description AS genderDescription,
            p.birth_date AS birthDate,
            p.address AS address,
            p.phone AS phone,
            p.email AS email,
            CASE WHEN p.is_active THEN 'Active' ELSE 'Inactive' END AS isActive,
            p.created_at AS createdAt,
            p.updated_at AS updatedAt,
            
            'EMPLOYEE' AS personType,

            -- Campos exclusivos Employee
            e.branch_id AS branchId,
            e.position AS position,
            e.salary AS salary,
            e.hire_date AS hireDate

        FROM person p
        INNER JOIN employee e ON p.person_id = e.person_id
        INNER JOIN identification_type it ON p.identification_type_id = it.identification_type_id
        INNER JOIN gender g ON p.gender_id = g.gender_id

        WHERE p.person_id > :lastPersonId
          AND p.is_active = TRUE

        ORDER BY p.person_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<PersonPageFullResponseDto> findAllActiveEmployees(
            @Param("lastPersonId") Integer lastPersonId,
            @Param("pageSize") Integer pageSize
    );

    // -------------------------------------------------------------------------
    // 2. LISTAR EMPLOYEES INACTIVOS (DTO GENERAL)
    // -------------------------------------------------------------------------
    @Query(value = """
        SELECT 
            p.person_id AS id,
            it.identification_type_id AS identificationTypeId,
            it.description AS identificationTypeDescription,
            p.identification_number AS identificationNumber,
            p.first_name AS firstName,
            p.last_name AS lastName,
            g.gender_id AS genderId,
            g.description AS genderDescription,
            p.birth_date AS birthDate,
            p.address AS address,
            p.phone AS phone,
            p.email AS email,
            CASE WHEN p.is_active THEN 'Active' ELSE 'Inactive' END AS isActive,
            p.created_at AS createdAt,
            p.updated_at AS updatedAt,
            
            'EMPLOYEE' AS personType,

            -- Campos exclusivos Employee
            e.branch_id AS branchId,
            e.position AS position,
            e.salary AS salary,
            e.hire_date AS hireDate

        FROM person p
        INNER JOIN employee e ON p.person_id = e.person_id
        INNER JOIN identification_type it ON p.identification_type_id = it.identification_type_id
        INNER JOIN gender g ON p.gender_id = g.gender_id

        WHERE p.person_id > :lastPersonId
          AND p.is_active = FALSE

        ORDER BY p.person_id
        LIMIT :pageSize
        """, nativeQuery = true)
    List<PersonPageFullResponseDto> findAllUnActiveEmployees(
            @Param("lastPersonId") Integer lastPersonId,
            @Param("pageSize") Integer pageSize
    );
}
