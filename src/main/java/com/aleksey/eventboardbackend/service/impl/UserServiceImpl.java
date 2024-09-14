package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.exception.user.UserNotFoundException;
import com.aleksey.eventboardbackend.repository.UserRepository;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
