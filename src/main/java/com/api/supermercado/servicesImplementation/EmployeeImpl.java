package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.repositories.EmployeeRepository;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

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
}
