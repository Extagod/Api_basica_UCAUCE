package com.api.supermercado.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "customer")
@DiscriminatorValue("CUSTOMER")
public class Customer extends Person{

    @PrimaryKeyJoinColumn(name = "person_id")
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "registration_date")
    private Instant registrationDate;

}