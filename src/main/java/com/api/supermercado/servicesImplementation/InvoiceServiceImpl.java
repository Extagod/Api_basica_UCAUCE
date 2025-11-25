package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.*;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;
import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.InvoiceException;
import com.api.supermercado.exceptions.InvoiceExceptions;
import com.api.supermercado.mappers.InvoiceMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.InvoiceDetailRepository;
import com.api.supermercado.repositories.InvoiceRepository;
import com.api.supermercado.services.InvoiceService;
import com.api.supermercado.services.IssuingCompanyService;
import com.api.supermercado.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {


    @Override
    public boolean validateInvoiceData(MinimumInvoiceRequestDto minimumInvoiceRequestDto) {
        return minimumInvoiceRequestDto.invoiceInfoDTO() != null
                && minimumInvoiceRequestDto.taxInfoDTO() != null
                && minimumInvoiceRequestDto.detalles() != null;
    }
}