package com.aleksey.eventboardbackend.repository;

import com.aleksey.eventboardbackend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
