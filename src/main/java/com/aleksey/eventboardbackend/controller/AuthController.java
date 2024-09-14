package com.aleksey.eventboardbackend.controller;

import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.token.TokenDto;
import com.aleksey.eventboardbackend.dto.user.LoginUserRequest;
import com.aleksey.eventboardbackend.dto.user.RegisterUserRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.service.AuthService;
import com.aleksey.eventboardbackend.util.ResponseBuilder;
import com.google.api.client.auth.oauth2.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aleksey.eventboardbackend.constants.Endpoints.*;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.USER_SUCCESSFULLY_LOGIN;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.USER_SUCCESSFULLY_REGISTERED;

@Slf4j
@RestController
@RequestMapping(AUTH_URL)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<DefaultResponse<UserDto>> registerUser(@Validated @RequestBody RegisterUserRequest registerUserRequest) {
        UserDto registeredUser = authService.registerUser(registerUserRequest);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(USER_SUCCESSFULLY_REGISTERED, registeredUser.getEmail()),
                        registeredUser
                )
        );
    }

    @PostMapping(LOGIN)
    public ResponseEntity<DefaultResponse<TokenDto>> loginUser(@Validated @RequestBody LoginUserRequest loginUserRequest) {
        TokenDto tokenDto = authService.loginUser(loginUserRequest);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(USER_SUCCESSFULLY_LOGIN, tokenDto.getEmail()),
                        tokenDto
                )
        );
    }
}
