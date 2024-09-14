package com.aleksey.eventboardbackend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
    DEANERY,
    MANAGER,
    STUDENT;

    public GrantedAuthority getSecurityRole() {
        return new SimpleGrantedAuthority("ROLE_" + this.name().toUpperCase());
    }
}
