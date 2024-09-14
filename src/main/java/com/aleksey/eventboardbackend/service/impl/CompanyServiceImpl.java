package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.exception.company.CompanyAlreadyExistsException;
import com.aleksey.eventboardbackend.exception.company.CompanyNotFoundException;
import com.aleksey.eventboardbackend.mapper.CompanyMapper;
import com.aleksey.eventboardbackend.repository.CompanyRepository;
import com.aleksey.eventboardbackend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService  {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    @Transactional(readOnly = true)
    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Override
    @Transactional
    public CompanyDto createCompany(CreateCompanyRequest createCompanyRequest) {
        if (companyRepository.existsByName(createCompanyRequest.getName())) {
            throw new CompanyAlreadyExistsException(createCompanyRequest.getName());
        }

        Company company = companyMapper.toEntity(createCompanyRequest);

        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyDto updateCompany(UUID id, UpdateCompanyRequest updateCompanyRequest) {
        if (companyRepository.existsByNameAndIdNot(updateCompanyRequest.getName().trim(), id)) {
            throw new CompanyAlreadyExistsException(updateCompanyRequest.getName());
        }

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        companyMapper.update(company, updateCompanyRequest);

        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto getCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public void deleteCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        companyRepository.delete(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDto> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable).map(companyMapper::toDto);
    }
}
