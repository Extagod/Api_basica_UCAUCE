package com.api.supermercado.repositories;

import com.api.supermercado.entities.Role;
import com.api.supermercado.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findById(Integer idRole);
    Optional <Role> findByDescription(String description);
}
