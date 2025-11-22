package com.api.supermercado.services;

import com.api.supermercado.dtos.AccessKeyDTO;
import com.api.supermercado.dtos.InvoiceInfoDTO;
import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;

public interface InvoiceService {
    String generateAccessKey(AccessKeyDTO dto);
    InvoiceResponseDto createInvoice(InvoiceRequestDto request);
    InvoiceResponseDto getInvoiceById(Integer id);
    InvoiceInfoDTO buildInvoiceInfo(Invoice invoice, Customer customer);
}
