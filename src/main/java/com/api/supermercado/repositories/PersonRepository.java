package com.api.supermercado.repositories;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

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
        
        'CUSTOMER' AS personType,

        -- Campos exclusivos
        c.registration_date AS registrationDate

    FROM person p
    INNER JOIN customer c ON p.person_id = c.person_id
    INNER JOIN identification_type it ON p.identification_type_id = it.identification_type_id
    INNER JOIN gender g ON p.gender_id = g.gender_id

    WHERE p.person_id > :lastPersonId
      AND p.is_active = TRUE

    ORDER BY p.person_id
    LIMIT :pageSize
""", nativeQuery = true)
    List<PersonPageFullResponseDto> findAllActiveCustomers(
            @Param("lastPersonId") Integer lastPersonId,
            @Param("pageSize") Integer pageSize
    );


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
        
        'CUSTOMER' AS personType,

        -- Campos exclusivos
        c.registration_date AS registrationDate

    FROM person p
    INNER JOIN customer c ON p.person_id = c.person_id
    INNER JOIN identification_type it ON p.identification_type_id = it.identification_type_id
    INNER JOIN gender g ON p.gender_id = g.gender_id

    WHERE p.person_id > :lastPersonId
      AND p.is_active = FALSE

    ORDER BY p.person_id
    LIMIT :pageSize
""", nativeQuery = true)
    List<PersonPageFullResponseDto> findAllUnActiveCustomers(
            @Param("lastPersonId") Integer lastPersonId,
            @Param("pageSize") Integer pageSize
    );



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

        -- Campos exclusivos
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

        -- Campos exclusivos
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



    Optional<Person> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = """
        SELECT
            p.first_name AS firstName,
            p.last_name AS lastName,
            p.identification_type_id AS identificationType,
            p.identification_number AS identificationNumber,
            p.email AS email,
            p.phone AS phone,
            p.address AS address,
            CASE WHEN p.is_active = TRUE THEN 'Active' ELSE 'Inactive' END AS isActive,
            p.created_at AS createdAt,
            p.updated_at AS updatedAt
        FROM person p
        WHERE p.identification_number = :identificationNumber
        LIMIT 1
        """, nativeQuery = true)
    Optional<PersonPageFullResponseDto> findPersonByIdentificationNumber(String identificationNumber);

    @Query(value = """
        SELECT
            p.first_name AS firstName,
            p.last_name AS lastName,
            p.identification_type_id AS identificationType,
            p.identification_number AS identificationNumber,
            p.email AS email,
            p.phone AS phone,
            p.address AS address,
            CASE WHEN p.is_active = TRUE THEN 'Active' ELSE 'Inactive' END AS isActive,
            p.created_at AS createdAt,
            p.updated_at AS updatedAt
        FROM person p
        WHERE p.identification_number = :email
        LIMIT 1
        """, nativeQuery = true)
    Optional<PersonPageFullResponseDto> findPersonByEmail(String email);
}
