package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.IssuingCompanyRegisterDto;
import com.api.supermercado.entities.IssuingCompany;
import com.api.supermercado.exceptions.IssuingCompanyException;
import com.api.supermercado.exceptions.IssuingCompanyExceptions;
import com.api.supermercado.mappers.IssuingCompanyMapper;
import com.api.supermercado.dtos.IssuingCompanyProjection;
import com.api.supermercado.repositories.IssuingCompanyRepository;
import com.api.supermercado.services.IssuingCompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssuingCompanyServiceImpl implements IssuingCompanyService {

    private final IssuingCompanyRepository repository;
    private final IssuingCompanyMapper mapper;

    // ---------------------------------------------------------
    // LIST ACTIVE
    // ---------------------------------------------------------
    @Override
    public List<IssuingCompanyProjection> listActiveIssuingCompanies(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;
        return repository.findActiveIssuingCompanies(lastId, size);
    }

    // ---------------------------------------------------------
    // LIST INACTIVE
    // ---------------------------------------------------------
    @Override
    public List<IssuingCompanyProjection> listInactiveIssuingCompanies(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;
        return repository.findInactiveIssuingCompanies(lastId, size);
    }

    // ---------------------------------------------------------
    // CREATE ISSUING COMPANY
    // ---------------------------------------------------------
    @Override
    public void createIssuingCompany(IssuingCompanyRegisterDto dto) {

        if (dto == null) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.INVALID_DATA);
        }

        // Check duplicate establishment code
        if (repository.existsByEstablishmentCode(dto.establishmentCode())) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.DUPLICATE_ESTABLISHMENT_CODE);
        }

        // Check duplicate RUC
        if (repository.findByRuc(dto.ruc()).isPresent()) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.DUPLICATE_RUC);
        }

        IssuingCompany issuingCompany = mapper.toEntity(dto);

        repository.save(issuingCompany);
    }

    // ---------------------------------------------------------
    // DELETE ISSUING COMPANY (LOGICAL)
    // ---------------------------------------------------------
    @Override
    public void deleteIssuingCompany(String establishmentCode) {
        if (establishmentCode == null || establishmentCode.isBlank()) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.INVALID_DATA);
        }

        IssuingCompany issuingCompany = repository.findByEstablishmentCode(establishmentCode)
                .orElseThrow(() -> new IssuingCompanyException(IssuingCompanyExceptions.COMPANY_NOT_FOUND));

        // Instead of deleting, we disable accounting (active flag equivalent)
        issuingCompany.setRequiresAccounting(false);

        repository.save(issuingCompany);
    }

    // ---------------------------------------------------------
    // UPDATE ISSUING COMPANY
    // ---------------------------------------------------------
    @Transactional
    @Override
    public Optional<IssuingCompanyProjection> updateIssuingCompany(String establishmentCode, IssuingCompanyRegisterDto dto) {
        if (establishmentCode == null || establishmentCode.isBlank()) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.INVALID_DATA);
        }

        IssuingCompany existing = repository.findByEstablishmentCode(establishmentCode)
                .orElseThrow(() -> new IssuingCompanyException(IssuingCompanyExceptions.COMPANY_NOT_FOUND));

        // Validate unique establishment code
        if (!existing.getEstablishmentCode().equalsIgnoreCase(dto.establishmentCode())
                && repository.existsByEstablishmentCode(dto.establishmentCode())) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.DUPLICATE_ESTABLISHMENT_CODE);
        }

        // Validate unique RUC
        if (!existing.getRuc().equalsIgnoreCase(dto.ruc())
                && repository.findByRuc(dto.ruc()).isPresent()) {
            throw new IssuingCompanyException(IssuingCompanyExceptions.DUPLICATE_RUC);
        }

        mapper.updateCompanyFromDto(dto, existing);

        repository.save(existing);

        return repository.findProjectionByEstablishmentCode(existing.getEstablishmentCode());
    }
}
