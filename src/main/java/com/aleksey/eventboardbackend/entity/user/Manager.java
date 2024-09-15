package com.aleksey.eventboardbackend.entity.user;

import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    private boolean confirmed = false;

    @Override
    public Role getRole() {
        return Role.MANAGER;
    }

    public boolean isInSameCompany(Company company) {
        return this.company.equals(company);
    }

    public void confirm() {
        this.confirmed = true;
    }
}
