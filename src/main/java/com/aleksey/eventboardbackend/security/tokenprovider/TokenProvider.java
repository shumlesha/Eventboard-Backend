package com.aleksey.eventboardbackend.security.tokenprovider;

import com.aleksey.eventboardbackend.enums.Role;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface TokenProvider {
    String createAccessToken(UUID userId, String email, Role role);
    boolean validateToken(String token);
    Authentication getAuthentication(String token);

}
