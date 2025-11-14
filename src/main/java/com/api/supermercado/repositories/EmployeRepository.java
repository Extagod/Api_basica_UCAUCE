package com.api.supermercado.repositories;

import com.api.supermercado.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employee, Integer>{
    boolean existsByUsername(String username);
}
