package com.aleksey.eventboardbackend.entity.user;

import com.aleksey.eventboardbackend.entity.Company;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private boolean confirmed = false;
}
