package com.api.supermercado.repositories;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<PersonPageFullResponseDto> findPersonsPage(
                @Param("lastPersonId") Integer lastPersonId,
                @Param("pageSize") Integer pageSize
        );
    }




