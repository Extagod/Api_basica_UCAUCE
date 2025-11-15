package com.api.supermercado.security;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Employee;
import com.api.supermercado.entities.Role;
import com.api.supermercado.mappers.CustomerMapper;
import com.api.supermercado.mappers.EmployeeMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.EmployeRepository;
import com.api.supermercado.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;
    private final EmployeRepository employeRepository;
    private final RoleRepository roleRepository;


    // ============================================================
    // 🟦 REGISTER CUSTOMER
    // ============================================================
    public void registerUser(CustomerRegisterDto request) {

        System.out.println("\n============================");
        System.out.println("📩 REGISTER CUSTOMER INIT");
        System.out.println("============================");
        System.out.println("➡️ DTO recibido: " + request);

        // MapStruct
        Customer customer = customerMapper.toEntity(request);
        System.out.println("🔄 CUSTOMER MAPEADO: " + customer);

        // Roles enviados en el JSON
        System.out.println("📌 Roles recibidos del DTO: " + request.roles());

        // Convertir IDs a entidades Role
        List<Role> roles = request.roles().stream()
                .map(roleId -> {
                    System.out.println("🔎 Buscando role ID: " + roleId);
                    return roleRepository.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("Role no encontrado: " + roleId));
                })
                .toList();

        System.out.println("✅ Roles asignados: " + roles);

        // Password
        System.out.println("🔐 Password sin codificar: " + request.password());
        customer.setPassword(passwordEncoder.encode(request.password()));
        System.out.println("🔐 Password codificado: " + customer.getPassword());

        customer.setIs_active(true);
        System.out.println("🟢 is_active = true");

        customer.setRoles(roles);

        System.out.println("💾 Guardando CUSTOMER en BD...");
        customerRepository.save(customer);

        System.out.println("🎉 CUSTOMER REGISTRADO EXITOSAMENTE");
    }


    // ============================================================
    // 🟩 REGISTER EMPLOYEE
    // ============================================================
    public void registerEmployee(EmployeeRegisterDto requestEmployee) {

        System.out.println("\n============================");
        System.out.println("📩 REGISTER EMPLOYEE INIT");
        System.out.println("============================");
        System.out.println("➡️ DTO recibido: " + requestEmployee);

        // MapStruct
        Employee employee = employeeMapper.toEntity(requestEmployee);
        System.out.println("🔄 EMPLOYEE MAPEADO: " + employee);

        // Roles enviados en el JSON
        System.out.println("📌 Roles recibidos del DTO: " + requestEmployee.roles());

        // Convertir IDs a entidades Role
        List<Role> roles = requestEmployee.roles().stream()
                .map(roleId -> {
                    System.out.println("🔎 Buscando role ID: " + roleId);
                    return roleRepository.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("Role no encontrado: " + roleId));
                })
                .toList();

        System.out.println("✅ Roles asignados: " + roles);

        // Password
        System.out.println("🔐 Password sin codificar: " + requestEmployee.password());
        employee.setPassword(passwordEncoder.encode(requestEmployee.password()));
        System.out.println("🔐 Password codificado: " + employee.getPassword());

        employee.setIs_active(true);
        System.out.println("🟢 is_active = true");

        employee.setRoles(roles);

        System.out.println("💾 Guardando EMPLOYEE en BD...");
        employeRepository.save(employee);

        System.out.println("🎉 EMPLOYEE REGISTRADO EXITOSAMENTE");
    }
}
