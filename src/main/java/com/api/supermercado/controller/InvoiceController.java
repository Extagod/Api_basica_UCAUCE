package com.api.supermercado.controller;


import com.api.supermercado.dtos.AccessKeyDTO;
import com.api.supermercado.dtos.AccessKeyPreviewResponse;
import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;
import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.enums.ReceiptTypeEnum;
import com.api.supermercado.services.InvoiceService;
import com.api.supermercado.utils.AccessKeyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;


    @GetMapping("/preview")
    public CompletableFuture<ResponseEntity<AccessKeyPreviewResponse>> preview(
            @RequestParam String ruc
    ) {
        // --- LOGS PARA VER EXACTAMENTE LO QUE ESTÁ LLEGANDO ---
        System.out.println("RUC recibido: [" + ruc + "] length=" + ruc.length());
        ruc.chars().forEach(c -> System.out.println("CHAR -> " + c));
        // --------------------------------------------------------

        return invoiceService.getIssuingCompanyByRuc(ruc)
                .thenCompose(issuingCompany ->
                        invoiceService.generateSequential(ruc, false)
                                .thenApply(sequential -> {

                                    AccessKeyDTO dto = new AccessKeyDTO(
                                            ReceiptTypeEnum.FACTURA,
                                            issuingCompany.getRuc(),
                                            issuingCompany.getEnvironmentType().toString(),
                                            sequential,
                                            "12345678",
                                            "1"
                                    );

                                    String clave = AccessKeyUtils.generarClaveAcceso(dto, issuingCompany);

                                    AccessKeyPreviewResponse response = new AccessKeyPreviewResponse(
                                            sequential,
                                            clave
                                    );

                                    return ResponseEntity.ok(response);
                                })
                );
    }


}
