package com.aleksey.eventboardbackend.security;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CurrentUser implements UserDetails {
    private final UUID id;
    private final String email;
    private final String fullName;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public boolean isManager() {
        return authorities.stream()
               .anyMatch(authority -> authority.getAuthority().equals("ROLE_MANAGER"));
    }

    public boolean isStudent() {
        return authorities.stream()
               .anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"));
    }

    public boolean isDeanery() {
        return authorities.stream()
               .anyMatch(authority -> authority.getAuthority().equals("ROLE_DEANERY"));
    }
}
