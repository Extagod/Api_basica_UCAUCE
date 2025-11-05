package com.api.supermercado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "supplier",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_supplier_tax_id", columnNames = "tax_id"),
                @UniqueConstraint(name = "uk_supplier_email", columnNames = "email")
        }
)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Integer supplierId;

    @NotBlank(message = "Company name is required.")
    @Size(max = 100, message = "Company name cannot exceed 100 characters.")
    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @NotBlank(message = "Tax ID is required.")
    @Pattern(regexp = "^[0-9]{13}$", message = "Tax ID must be exactly 13 digits.")
    @Column(name = "tax_id", nullable = false, length = 13)
    private String taxId;

    @Size(max = 200, message = "Address cannot exceed 200 characters.")
    @Column(name = "address", length = 200)
    private String address;

    @Pattern(regexp = "^[0-9]{7,15}$", message = "Phone number must contain only digits and be between 7 and 15 characters long.")
    @Column(name = "phone", length = 20)
    private String phone;

    @Email(message = "Email must be a valid format.")
    @Size(max = 100, message = "Email cannot exceed 100 characters.")
    @Column(name = "email", length = 100)
    private String email;

    @NotNull(message = "It must be specified whether the supplier is active or not.")
    @Column(name = "is_active")
    private Boolean is_active;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
