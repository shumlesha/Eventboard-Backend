package com.aleksey.eventboardbackend.mapper;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.entity.Company;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(target = "managers", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    @Mapping(target = "name", expression = "java(createCompanyRequest.getName().trim())")
    Company toEntity(CreateCompanyRequest createCompanyRequest);

    CompanyDto toDto(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "name", expression = "java(updateCompanyRequest.getName().trim())")
    void update(@MappingTarget Company company, UpdateCompanyRequest updateCompanyRequest);
}
