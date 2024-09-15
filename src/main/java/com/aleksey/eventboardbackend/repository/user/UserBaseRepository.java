package com.aleksey.eventboardbackend.repository.user;

import com.aleksey.eventboardbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserBaseRepository<T extends User> extends JpaRepository<T, UUID> {
    Optional<T> findByEmail(String email);
}
