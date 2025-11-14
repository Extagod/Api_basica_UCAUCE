package com.api.supermercado.servicesImplementation;

import com.api.supermercado.entities.Customer;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.security.Role;
import com.api.supermercado.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
    @RequiredArgsConstructor
    public class CustomerImpl implements CustomerService {

        private final CustomerRepository customerRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public void createCustomer() {

            Customer customer = new Customer();

            // 🔹 Datos heredados de Person
            customer.setIdIdentificationType(1); // Cédula o lo que corresponda
            customer.setIdentificationNumber("0213456789");

            customer.setFirstName("Jeicob");
            customer.setLastName("Perez");

            customer.setGenderId(1); // Masculino/femenino según tu catálogo

            customer.setBirthDate(LocalDate.parse("2003-07-15")); // ✔ LocalDate, no String

            customer.setAddress("Calle 123 #123");
            customer.setPhone("1112345678");
            customer.setEmail("jeico123@gmail.com");
            customer.setUsername("jeicob123");

            // ✔ Las contraseñas SIEMPRE se encriptan
            customer.setPassword(passwordEncoder.encode("jeico123"));

            // Rol heredado de Person
            customer.setRoleId(Role.USER);

            customer.setIs_active(true);

            // 🔹 Campo propio de Customer
            customer.setRegistrationDate(java.time.Instant.now());

            // ✔ Guardar en BD
            customerRepository.save(customer);

            System.out.println("Customer creado correctamente!");
        }
    }

