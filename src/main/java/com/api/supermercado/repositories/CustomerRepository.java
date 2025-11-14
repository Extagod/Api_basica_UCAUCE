package com.api.supermercado.repositories;

import com.api.supermercado.dtos.CustomerPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

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
        p.updated_at AS updatedAt,
        c.registration_date AS registrationDate
    FROM person p
    INNER JOIN customer c ON c.person_id = p.person_id
    WHERE p.identification_number = :identificationNumber
    LIMIT 1
    """, nativeQuery = true)
    Optional<CustomerPageFullResponseDto> findCustomerByIdentificationNumber(String identificationNumber);

}
