package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeePageFullResponseDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Employee;
import com.api.supermercado.entities.Person;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.EmployeeRepository;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final PersonRequestMapper personRequestMapper;

    @Override
        public List<PersonPageFullResponseDto> findAllActiveEmployees(Integer lastPersonId, Integer size) {
            int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
            int pageSize = (size == null || size <= 0) ? 10 : size;

            return employeeRepository.findAllActiveEmployees(last, pageSize);
        }

    @Override
    public List<PersonPageFullResponseDto> findAllUnActiveEmployees(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return employeeRepository.findAllUnActiveEmployees(last, pageSize);
    }


    @Transactional
    @Override
    public Optional<Person> updateEmployee(String identificationNumber, EmployeeRegisterDto dto) {

        if (identificationNumber == null || identificationNumber.isBlank() || dto == null) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }

        // 1️⃣ Buscar EMPLOYEE existente
        Employee existing = employeeRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        // 2️⃣ Verificar duplicado en la cédula SI cambia
        if (!existing.getIdentificationNumber().equalsIgnoreCase(dto.identificationNumber()) &&
                personRepository.existsByIdentificationNumber(dto.identificationNumber())) {
            throw new PersonException(PersonExceptions.DUPLICATE_IDENTIFICATION);
        }

        // 3️⃣ Actualizar los campos base de PERSON (lo hace MapStruct)
        personRequestMapper.updatePersonWithDto(dto, existing);

        // 4️⃣ Actualizar los campos exclusivos de EMPLOYEE
        existing.setBranchId(dto.branchId());
        existing.setPosition(dto.position());
        existing.setSalary(dto.salary());
        existing.setHireDate(dto.hireDate());

        // 5️⃣ Guardar EMPLOYEE (esto actualiza también PERSON)
        Employee updated = employeeRepository.save(existing);

        return Optional.of(updated);
    }

    @Override
    public void deleteEmployee(String identificationNumber) {

        if (identificationNumber == null || identificationNumber.isBlank()) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }

        Employee existing = employeeRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        // Borrado lógico
        existing.setIs_active(false);

        employeeRepository.save(existing);
    }

    @Override
    public Optional<EmployeePageFullResponseDto> findEmployeeByIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.isBlank()) {
            throw new PersonException(PersonExceptions.INVALID_PERSON_DATA);
        }
        return employeeRepository.findEmployeeByIdentificationNumber(identificationNumber)
                .stream()
                .findFirst();
    }

}
