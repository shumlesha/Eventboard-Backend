package com.aleksey.eventboardbackend.security;

import com.aleksey.eventboardbackend.entity.user.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserFactory {
    public CurrentUser create(User user) {
        return new CurrentUser(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                List.of(user.getRole().getSecurityRole())
        );
    }
}
