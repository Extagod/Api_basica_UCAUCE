package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.AccessKeyDTO;
import com.api.supermercado.dtos.InvoiceRequestDto;
import com.api.supermercado.dtos.InvoiceResponseDto;
import com.api.supermercado.entities.Invoice;
import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.exceptions.InvoiceException;
import com.api.supermercado.exceptions.InvoiceExceptions;
import com.api.supermercado.mappers.InvoiceMapper;
import com.api.supermercado.repositories.InvoiceRepository;
import com.api.supermercado.services.InvoiceService;
import com.api.supermercado.services.IssuingCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceMapper mapper;
    private final IssuingCompanyService issuingCompanyService;
    private final InvoiceRepository invoiceRepository; // <-- Necesario para guardar

    // =========================================================
    //            GENERATE ACCESS KEY (SRI)
    // =========================================================
    @Override
    public String generateAccessKey(AccessKeyDTO dto) {

        LocalDate date = dto.Date();
        String dd = String.format("%02d", date.getDayOfMonth());
        String mm = String.format("%02d", date.getMonthValue());
        String yyyy = String.valueOf(date.getYear());

        String base = dd
                + mm
                + yyyy
                + dto.ReceiptType()
                + dto.TaxpayerID()
                + dto.Environment()
                + dto.Series()
                + dto.Sequential()
                + dto.NumericCode()
                + dto.IssueType();

        String checkDigit = getCheckDigit(base);

        return base + checkDigit;
    }

    // =========================================================
    //            CREATE INVOICE (FULL LOGIC HERE)
    // =========================================================
    @Override
    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        Invoice invoice = mapper.toEntity(request);

        // 1️⃣ Find Issuing Company
        IssuingCompany company = issuingCompanyService
                .findById(invoice.getIssuingCompanyId())
                .orElseThrow(() ->
                        new InvoiceException(InvoiceExceptions.INVALID_ISSUING_COMPANY_ID)
                );

        // 2️⃣ Generate Sequential
        String sequential = generateNextSequential(company.getId());
        invoice.setSequential(sequential);

// 2.1️⃣ Generate Invoice Number (000-000-000000001)
        String invoiceNumber = generateInvoiceNumber(company, sequential);
        invoice.setInvoiceNumber(invoiceNumber);


        // 3️⃣ Build AccessKey DTO
        AccessKeyDTO accessKeyDTO = new AccessKeyDTO(
                LocalDate.now(),
                "01",
                company.getRuc(),
                String.valueOf(company.getEnvironmentType()),
                company.getSeries(),
                sequential,
                "12345678",
                "1"
        );

        // 4️⃣ Generate Access Key
        String accessKey = generateAccessKey(accessKeyDTO);
        invoice.setAccessKey(accessKey);

        // 5️⃣ Set defaults
        invoice.setIssueDate(Instant.now());
        invoice.setStatus("PENDING");

        // 6️⃣ Save Invoice
        invoiceRepository.save(invoice);

        // 7️⃣ Return projection as Response DTO
        return mapper.toResponse(
                invoiceRepository.findByIdProjection(invoice.getId())
        );
    }


    private String generateInvoiceNumber(IssuingCompany company, String sequential) {
        return company.getEstablishmentCode()
                + "-" +
                company.getEmissionPoint()
                + "-" +
                sequential;
    }


    @Override
    public InvoiceResponseDto getInvoiceById(Integer id) {

        var projection = invoiceRepository.findByIdProjection(id);

        if (projection == null) {
            throw new InvoiceException(InvoiceExceptions.INVOICE_NOT_FOUND);
        }

        return mapper.toResponse(projection);
    }


    // =========================================================
    //            SEQUENTIAL GENERATOR (HERE INSIDE)
    // =========================================================
    private String generateNextSequential(Integer issuingCompanyId) {

        String lastSequential = invoiceRepository.findLastSequentialByCompanyId(issuingCompanyId);

        if (lastSequential == null) {
            return "000000001"; // default first sequential
        }

        // Convert to integer
        int nextNumber = Integer.parseInt(lastSequential) + 1;

        // Pad with zeros up to 9 digits (SRI requirement)
        return String.format("%09d", nextNumber);
    }

    // =========================================================
    //            MODULE 11 CHECK DIGIT CALCULATOR
    // =========================================================
    private String getCheckDigit(String base) {
        int[] coef = {2, 3, 4, 5, 6, 7};
        int sum = 0;
        int j = 0;

        for (int i = base.length() - 1; i >= 0; i--, j++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * coef[j % coef.length];
        }

        int mod = 11 - (sum % 11);

        if (mod == 11) return "0";
        if (mod == 10) return "1";
        return String.valueOf(mod);
    }
}
