package com.api.supermercado.services;

import com.api.supermercado.dtos.*;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;

public interface InvoiceService {
    String generateAccessKey(AccessKeyDTO dto);
    InvoiceResponseDto createInvoice(InvoiceRequestDto request);
    InvoiceResponseDto getInvoiceById(Integer id);
    InvoiceInfoDTO buildInvoiceInfo(Invoice invoice, Customer customer);
    MinimumInvoiceRequestDto buildInvoiceRequest(Integer invoiceId);
}
