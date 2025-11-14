package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employee")
@DiscriminatorValue("EMPLOYEE")

public class Employee extends Person {

    @PrimaryKeyJoinColumn(name = "person_id")

    @Column(name = "branch_id", nullable = false)
    private Integer branchId;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "hire_date")
    private LocalDate hireDate;



}