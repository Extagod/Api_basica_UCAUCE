package com.api.supermercado.security;


import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.dtos.PersonRequestRegisterDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Employee;
import com.api.supermercado.entities.Person;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.CustomerMapper;
import com.api.supermercado.mappers.EmployeeMapper;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.EmployeRepository;
import com.api.supermercado.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;
    private final EmployeRepository employeRepository;



    public void registerUser(CustomerRegisterDto request) {

        Customer customer =customerMapper.toEntity(request);
        customer.setIs_active(true);
        customer.setPassword(passwordEncoder.encode(request.password()));
        customer.setRoleId(Role.USER);
        customerRepository.save(customer);
    }

    public void registerEmployee(EmployeeRegisterDto requestEmployee) {
        Employee employee =employeeMapper.toEntity(requestEmployee);
        employee.setIs_active(true);
        employee.setPassword(passwordEncoder.encode(requestEmployee.password()));
        employee.setRoleId(Role.USER);
        employeRepository.save(employee);
    }
}
