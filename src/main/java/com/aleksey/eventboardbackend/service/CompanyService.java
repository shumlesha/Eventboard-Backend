package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
    Company getById(UUID id);

    CompanyDto createCompany(CreateCompanyRequest createCompanyRequest);

    CompanyDto updateCompany(UUID id, UpdateCompanyRequest updateCompanyRequest);

    CompanyDto getCompany(UUID id);

    void deleteCompany(UUID id);

    Page<CompanyDto> getAllCompanies(Pageable pageable);
}
