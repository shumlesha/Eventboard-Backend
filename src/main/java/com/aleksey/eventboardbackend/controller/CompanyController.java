package com.aleksey.eventboardbackend.controller;


import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.service.CompanyService;
import com.aleksey.eventboardbackend.util.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.aleksey.eventboardbackend.constants.Endpoints.COMPANY_URL;
import static com.aleksey.eventboardbackend.constants.Endpoints.ID;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.*;
import static com.aleksey.eventboardbackend.constants.messages.SwaggerMessages.*;

@RestController
@RequestMapping(COMPANY_URL)
@PreAuthorize("hasRole('DEANERY')")
@RequiredArgsConstructor
@Tag(name = COMPANY_TAG)
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @Operation(summary = COMPANIES_CREATE_SUMMARY, description = COMPANIES_CREATE_DESCRIPTION)
    public ResponseEntity<DefaultResponse<CompanyDto>> createCompany(@Validated @RequestBody CreateCompanyRequest createCompanyRequest) {
        CompanyDto createdCompany = companyService.createCompany(createCompanyRequest);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(COMPANY_SUCCESSFULLY_CREATED, createdCompany.getName()),
                        createdCompany
                )
        );
    }

    @PatchMapping(ID)
    @Operation(summary = COMPANIES_UPDATE_SUMMARY, description = COMPANIES_UPDATE_DESCRIPTION)
    public ResponseEntity<DefaultResponse<CompanyDto>> updateCompany(@PathVariable UUID id,
                                                                     @Validated @RequestBody UpdateCompanyRequest updateCompanyRequest) {
        CompanyDto updatedCompany = companyService.updateCompany(id, updateCompanyRequest);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(COMPANY_SUCCESSFULLY_UPDATED, id),
                        updatedCompany
                )
        );
    }

    @GetMapping(ID)
    @Operation(summary = COMPANIES_GET_SUMMARY, description = COMPANIES_GET_DESCRIPTION)
    public ResponseEntity<DefaultResponse<CompanyDto>> getCompany(@PathVariable UUID id) {
        CompanyDto company = companyService.getCompany(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(COMPANY_SUCCESSFULLY_RETRIEVED, company.getId()),
                        company)
        );
    }

    @DeleteMapping(ID)
    @Operation(summary = COMPANIES_DELETE_SUMMARY, description = COMPANIES_DELETE_DESCRIPTION)
    public ResponseEntity<DefaultResponse<Void>> deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(COMPANY_SUCCESSFULLY_DELETED, id)
                )
        );
    }

    @GetMapping
    @Operation(summary = COMPANIES_GETALL_SUMMARY, description = COMPANIES_GETALL_DESCRIPTION)
    public ResponseEntity<DefaultResponse<Page<CompanyDto>>> getAllCompanies(@ParameterObject @PageableDefault(sort = "name",
            direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CompanyDto> companies = companyService.getAllCompanies(pageable);
        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(COMPANIES_SUCCESSFULLY_RETRIEVED, companies.getTotalElements()),
                        companies
                )
        );
    }
}
