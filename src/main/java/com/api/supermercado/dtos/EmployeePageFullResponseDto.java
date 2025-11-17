package com.api.supermercado.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EmployeePageFullResponseDto {
    Integer getId();
    String getFirstName();
    String getLastName();
    Integer getIdentificationTypeId();
    String getIdentificationTypeDescription();
    String getIdentificationNumber();
    String getEmail();
    String getPhone();
    String getAddress();
    String getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    // Campos exclusivos Employee
    Integer getBranchId();
    String getPosition();
    BigDecimal getSalary();
    LocalDate getHireDate();
}
