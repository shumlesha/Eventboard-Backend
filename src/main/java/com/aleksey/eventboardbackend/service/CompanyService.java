package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
    Company getById(UUID id);

    CompanyDto createCompany(CurrentUser currentUser, CreateCompanyRequest createCompanyRequest);

    CompanyDto updateCompany(UUID id, CurrentUser currentUser, UpdateCompanyRequest updateCompanyRequest);

    CompanyDto getCompany(UUID id, CurrentUser currentUser);

    void deleteCompany(UUID id);

    Page<CompanyDto> getAllCompanies(CurrentUser currentUser, Pageable pageable);
}
