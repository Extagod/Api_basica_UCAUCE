package com.api.supermercado.services;

import com.api.supermercado.dtos.*;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;
import com.api.supermercado.entities.IssuingCompany;

import java.util.concurrent.CompletableFuture;

public interface InvoiceService {
    boolean validateInvoiceData(MinimumInvoiceRequestDto minimumInvoiceRequestDto);
    CompletableFuture<String> generateSequential(String ruc, boolean saveOnly);

}
