package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.entity.Company;

import java.util.UUID;

public interface CompanyService {
    Company getById(UUID id);
}
