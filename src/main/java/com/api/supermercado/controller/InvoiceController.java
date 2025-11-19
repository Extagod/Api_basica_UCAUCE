package com.api.supermercado.controller;


import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;
import com.api.supermercado.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    // ============================
    // POST: CREATE INVOICE
    // ============================
    @PostMapping
    public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto request) {
        InvoiceResponseDto response = invoiceService.createInvoice(request);
        return ResponseEntity.ok(response);
    }

    // ============================
    // GET: GET INVOICE BY ID
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Integer id) {
        InvoiceResponseDto response = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(response);
    }
}
