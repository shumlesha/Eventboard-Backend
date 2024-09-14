package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.exception.company.CompanyNotFoundException;
import com.aleksey.eventboardbackend.repository.CompanyRepository;
import com.aleksey.eventboardbackend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService  {
    private final CompanyRepository companyRepository;

    @Override
    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }
}
