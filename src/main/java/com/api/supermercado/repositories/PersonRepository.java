package com.api.supermercado.repositories;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Person> findByIdentificationNumber(String identificationNumber);
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
    boolean existsByIdentificationNumber(String s);
}
