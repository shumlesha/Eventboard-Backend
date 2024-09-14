package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.token.TokenDto;
import com.aleksey.eventboardbackend.dto.user.LoginUserRequest;
import com.aleksey.eventboardbackend.dto.user.RegisterUserRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;

public interface AuthService {
    UserDto registerUser(RegisterUserRequest registerUserRequest);

    TokenDto loginUser(LoginUserRequest loginUserRequest);
}
