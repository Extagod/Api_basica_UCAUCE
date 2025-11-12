package com.api.supermercado.repositories;

import com.api.supermercado.entities.IdentificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {
}
