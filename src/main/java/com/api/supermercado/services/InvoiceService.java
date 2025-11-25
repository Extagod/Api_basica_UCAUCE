package com.api.supermercado.services;

import com.api.supermercado.dtos.*;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;

public interface InvoiceService {
    boolean validateInvoiceData(MinimumInvoiceRequestDto minimumInvoiceRequestDto);

}
