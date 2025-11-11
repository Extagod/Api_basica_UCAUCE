package com.api.supermercado.repositories;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
agit<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {



        @Query(value = """
        SELECT 
            p.person_id AS id,
            p.identification_type_id AS identificationType,
            p.identification_number AS identificationNumber,
            p.first_name AS firstName,
            p.is_active AS is_active,
            p.last_name AS lastName,
            p.gender_id AS gender,
            p.birth_date AS birthDate,
            p.address AS address,
            p.phone AS phone,
            p.email AS email
        FROM person p
        WHERE p.person_id > :lastPersonId
          AND p.is_active = true   -- Filtro agregado
        ORDER BY p.person_id
        LIMIT :pageSize
        """,
                nativeQuery = true)
<<<<<<< HEAD
        List<PersonPageFullResponseDto> findAllAvailablePersons(
                @Param("lastPersonId") Integer lastPersonId,
                @Param("pageSize") Integer pageSize
        );

    Optional<Person> findByUsername(String username);

    boolean existsByUsername(String username);


    @Query(value = """
        SELECT 
            p.person_id AS id,
            p.identification_type_id AS identificationType,
            p.identification_number AS identificationNumber,
            p.first_name AS firstName,
            p.is_active AS is_active,
            p.last_name AS lastName,
            p.gender_id AS gender,
            p.birth_date AS birthDate,
            p.address AS address,
            p.phone AS phone,
            p.email AS email
        FROM person p
        WHERE p.person_id > :lastPersonId
          AND p.is_active = false   
        ORDER BY p.person_id
        LIMIT :pageSize
        """,
            nativeQuery = true)
    List<PersonPageFullResponseDto> findAllUnAvailablePersons(
            @Param("lastPersonId") Integer lastPersonId,
            @Param("pageSize") Integer pageSize
    );


    @Query(value = """
        SELECT
            p.identification_type_id AS identificationType,
            p.identification_number AS identificationNumber,
            p.first_name AS firstName,
            p.last_name AS lastName,
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

}

=======
        List<PersonPageFullResponseDto> findPersonsPage(
                @Param("lastPersonId") Integer lastPersonId,
                @Param("pageSize") Integer pageSize
        );
    }
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb




