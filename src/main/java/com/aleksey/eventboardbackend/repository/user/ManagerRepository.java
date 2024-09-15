package com.aleksey.eventboardbackend.repository.user;

import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.user.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends UserBaseRepository<Manager> {
    Page<Manager> findAllByCompany(Company company, Pageable pageable);
}
