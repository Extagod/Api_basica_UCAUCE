package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.*;
import com.api.supermercado.entities.Customer;
import com.api.supermercado.entities.Invoice;
import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.InvoiceException;
import com.api.supermercado.exceptions.InvoiceExceptions;
import com.api.supermercado.exceptions.IssuingCompanyException;
import com.api.supermercado.exceptions.IssuingCompanyExceptions;
import com.api.supermercado.mappers.InvoiceMapper;
import com.api.supermercado.repositories.CustomerRepository;
import com.api.supermercado.repositories.InvoiceDetailRepository;
import com.api.supermercado.repositories.InvoiceRepository;
import com.api.supermercado.repositories.IssuingCompanyRepository;
import com.api.supermercado.services.InvoiceService;
import com.api.supermercado.services.IssuingCompanyService;
import com.api.supermercado.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private  final IssuingCompanyRepository issuingCompanyRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public boolean validateInvoiceData(MinimumInvoiceRequestDto minimumInvoiceRequestDto) {
        return minimumInvoiceRequestDto.invoiceInfoDTO() != null
                && minimumInvoiceRequestDto.taxInfoDTO() != null
                && minimumInvoiceRequestDto.detalles() != null;
    }

    @Override
    public CompletableFuture<String> generateSequential(String ruc, boolean saveOnly) {

        return CompletableFuture.supplyAsync(() -> {

            // 1️⃣ Buscar empresa emisora
            IssuingCompany issuingCompany = issuingCompanyRepository
                    .findByRucTrimmed(ruc)
                    .orElseThrow(() ->
                            new IssuingCompanyException(IssuingCompanyExceptions.COMPANY_NOT_FOUND)
                    );

            Integer companyId = issuingCompany.getIdIssuingCompany();

            // 2️⃣ Si NO es saveOnly → buscar última AUTORIZADA
            if (!saveOnly) {

                Optional<Invoice> lastAuthorized =
                        invoiceRepository.findLastAuthorizedInvoice(companyId, "AUTORIZADO");

                if (lastAuthorized.isPresent()) {
                    String lastSequential = lastAuthorized.get().getSequential();
                    int next = Integer.parseInt(lastSequential) + 1;
                    return String.format("%09d", next);
                } else {
                    return "000000001";
                }

            } else {
                // 3️⃣ Si es saveOnly → buscar última NO_ENVIADA
                Optional<Invoice> lastNoEnviada =
                        invoiceRepository.findLastInvoiceByStatus(companyId, "NO_ENVIADA");

                if (lastNoEnviada.isPresent()) {
                    String lastSequential = lastNoEnviada.get().getSequential();
                    int next = Integer.parseInt(lastSequential) + 1;
                    return String.format("%09d", next);
                } else {
                    return "000000001";
                }
            }
        });
    }

    @Override
    public CompletableFuture<IssuingCompany> getIssuingCompanyByRuc(String ruc) {
        return CompletableFuture.supplyAsync(() ->
                issuingCompanyRepository.findByRucTrimmed(ruc)
                        .orElseThrow(() ->
                                new IssuingCompanyException(IssuingCompanyExceptions.COMPANY_NOT_FOUND)
                        )
        );
    }
}