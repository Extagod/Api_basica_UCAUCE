package com.api.supermercado.entities;

import com.api.supermercado.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @NotNull(message = "Identification type is required.")
    @Column(name = "identification_type_id", nullable = false)
    private Integer idIentificationType;

    @NotBlank(message = "Identification number is required.")
    @Size(max = 20, message = "Identification number cannot exceed 20 characters.")
    @Column(name = "identification_number", nullable = false, length = 20)
    private String identificationNumber;

    @NotBlank(message = "First name is required.")
    @Size(max = 100, message = "First name cannot exceed 100 characters.")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 100, message = "Last name cannot exceed 100 characters.")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "Gender is required.")
    @Column(name = "gender_id", nullable = false)
    private Integer genderId;

    @Past(message = "Birth date must be in the past.")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 200, message = "Address cannot exceed 200 characters.")
    @Column(name = "address", length = 200)
    private String address;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters.")
    @Column(name = "phone", length = 20)
    private String phone;

    @Email(message = "Email must be a valid format.")
    @Size(max = 100, message = "Email cannot exceed 100 characters.")
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 30, message = "Username cannot exceed 30 characters.")
    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    // Data Persist
    @NotNull(message = "It must be specified whether the user is active or not")
    @Column(name = "is_active")
    private Boolean is_active;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updated_at;



}
