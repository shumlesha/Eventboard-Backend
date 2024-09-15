package com.aleksey.eventboardbackend.entity.user;

import com.aleksey.eventboardbackend.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEANERY")
public class Deanery extends User {

    @Override
    public Role getRole() {
        return Role.DEANERY;
    }

    @Override
    public boolean isConfirmed() {
        return true;
    }
}
