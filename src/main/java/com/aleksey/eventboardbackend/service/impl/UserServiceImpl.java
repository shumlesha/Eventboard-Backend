package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.exception.user.UserNotFoundException;
import com.aleksey.eventboardbackend.repository.UserRepository;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
