package com.api.supermercado.repositories;

import com.api.supermercado.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
