package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    // Active Customers
    @Override
    public List<PersonPageFullResponseDto> findAllActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return customerRepository.findAllActiveCustomers(last, pageSize); // ✅ CORRECTO
    }

    // Inactive Customers
    @Override
    public List<PersonPageFullResponseDto> findAllUnActiveCustomers(Integer lastPersonId, Integer size) {
        int last = (lastPersonId == null || lastPersonId < 0) ? 0 : lastPersonId;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        return customerRepository.findAllUnActiveCustomers(last, pageSize); // ✅ CORRECTO
    }
}

