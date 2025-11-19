package com.api.supermercado.services;

import com.api.supermercado.dtos.IssuingCompanyProjection;
import com.api.supermercado.dtos.IssuingCompanyRegisterDto;
import com.api.supermercado.entities.IssuingCompany;

import java.util.List;
import java.util.Optional;

public interface IssuingCompanyService {

    List<IssuingCompanyProjection> listActiveIssuingCompanies(Integer lastId, Integer size);

    List<IssuingCompanyProjection> listInactiveIssuingCompanies(Integer lastId, Integer size);

    void createIssuingCompany(IssuingCompanyRegisterDto dto);

    void deleteIssuingCompany(String establishmentCode);

    Optional<IssuingCompanyProjection> updateIssuingCompany(String establishmentCode, IssuingCompanyRegisterDto dto);
    Optional<IssuingCompany> findById(Integer id);


}
