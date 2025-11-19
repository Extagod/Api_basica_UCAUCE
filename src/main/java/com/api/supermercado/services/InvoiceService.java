package com.api.supermercado.services;

import com.api.supermercado.dtos.AccessKeyDTO;
import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;

public interface InvoiceService {
    String generateAccessKey(AccessKeyDTO dto);
    InvoiceResponseDto createInvoice(InvoiceRequestDto request);
    InvoiceResponseDto getInvoiceById(Integer id);

}
