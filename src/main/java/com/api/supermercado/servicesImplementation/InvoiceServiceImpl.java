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

    private final InvoiceMapper mapper;
    private final IssuingCompanyService issuingCompanyService;
    private final InvoiceRepository invoiceRepository; // <-- Necesario para guardar
    private final ProductService productService;
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final CustomerRepository customerRepository;

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

        // 1️⃣ Get Issuing Company
        IssuingCompany company = issuingCompanyService
                .findById(invoice.getIssuingCompanyId())
                .orElseThrow(() ->
                        new InvoiceException(InvoiceExceptions.INVALID_ISSUING_COMPANY_ID)
                );

        // 2️⃣ Sequential
        String sequential = generateNextSequential(company.getId());
        invoice.setSequential(sequential);

        // 3️⃣ Invoice Number
        String invoiceNumber = generateInvoiceNumber(company, sequential);
        invoice.setInvoiceNumber(invoiceNumber);

        // 4️⃣ Access Key
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

        String accessKey = generateAccessKey(accessKeyDTO);
        invoice.setAccessKey(accessKey);

        // 5️⃣ Tax Info DTO (internal)
        TaxInfoDTO taxInfo = new TaxInfoDTO(
                company.getRuc(),
                sequential,
                accessKey
        );

        // 6️⃣ Set defaults
        invoice.setIssueDate(Instant.now());
        invoice.setStatus("PENDING");

        // 7️⃣ Save
        invoiceRepository.save(invoice);

        // 8️⃣ Return response
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

    @Override
    public InvoiceInfoDTO buildInvoiceInfo(Invoice invoice, Customer customer) {
        String fullName = customer.getFirstName() + " " + customer.getLastName();

        return new InvoiceInfoDTO(
                invoice.getIssueDate().toString(),
                customer.getIdIdentificationType().toString(),
                customer.getIdentificationNumber(),
                fullName,
                invoice.getSubtotalWithoutTax().toString(),
                invoice.getTotalWithTax().toString()
        );
    }


    @Override
    public MinimumInvoiceRequestDto buildInvoiceRequest(Integer invoiceId) {

        // 1️⃣ Obtener invoice completa
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceException(InvoiceExceptions.INVOICE_NOT_FOUND));

        // 2️⃣ Traer cliente
        Customer customer = customerRepository.findById(invoice.getCustomerId())
                .orElseThrow(() -> new InvoiceException(InvoiceExceptions.CUSTOMER_NOT_FOUND));

        // 3️⃣ Traer detalles (productos en la factura)
        var invoiceDetails = invoiceDetailRepository.findByInvoiceId(invoiceId);

        if (invoiceDetails.isEmpty()) {
            throw new InvoiceException(InvoiceExceptions.EMPTY_INVOICE_DETAIL);
        }

        // 4️⃣ Convertir invoiceDetails → ProductDetailDto usando ProductService
        List<ProductDetailDto> detalles = invoiceDetails.stream()
                .map(det -> {
                    Product product = productService.findById(det.getProductId());
                    return productService.buildProductDetail(product, det.getQuantity());
                })
                .toList();

        // 5️⃣ Construir infoTributaria (lo que pide el SRI)
        TaxInfoDTO taxInfoDTO = new TaxInfoDTO(
                invoice.getIssuingCompanyId().toString(),  // ❗ debes reemplazar esto cuando tengas el RUC REAL
                invoice.getSequential(),
                invoice.getAccessKey()
        );

        // 6️⃣ Construir infoFactura
        InvoiceInfoDTO invoiceInfoDTO = buildInvoiceInfo(invoice, customer);

        // 7️⃣ Retornar estructura final EXACTA SRI
        return new MinimumInvoiceRequestDto(
                taxInfoDTO,
                invoiceInfoDTO,
                detalles
        );
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
