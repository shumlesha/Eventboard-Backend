package com.aleksey.eventboardbackend.mapper;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.User;
import java.util.Collections;

import com.aleksey.eventboardbackend.security.CurrentUser;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CompanyMapper {
    @Mapping(target = "managers", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    @Mapping(target = "name", expression = "java(createCompanyRequest.getName().trim())")
    Company toEntity(CreateCompanyRequest createCompanyRequest);

    @Mapping(target = "managers", expression = "java(mapManagers(company, currentUser))")
    @Mapping(target = "id", source = "company.id")
    CompanyDto toDto(Company company, @Context CurrentUser currentUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "name", expression = "java(updateCompanyRequest.getName().trim())")
    void update(@MappingTarget Company company, UpdateCompanyRequest updateCompanyRequest);

    default Set<ManagerDto> mapManagers(Company company, @Context CurrentUser currentUser) {
        boolean employeeOrDeanery = company.containsManagerByEmail(currentUser.getEmail()) || currentUser.isDeanery();
        if (currentUser.isStudent() || !employeeOrDeanery) {
            return Collections.emptySet();
        }
        return company.getManagers().stream()
                .map(this::mapManager)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "companyId", source = "company.id")
    ManagerDto mapManager(Manager manager);
}
