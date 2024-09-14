package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.dto.token.TokenDto;
import com.aleksey.eventboardbackend.dto.user.LoginUserRequest;
import com.aleksey.eventboardbackend.dto.user.RegisterUserRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.exception.user.UserAlreadyExistsException;
import com.aleksey.eventboardbackend.mapper.UserMapper;
import com.aleksey.eventboardbackend.repository.UserRepository;
import com.aleksey.eventboardbackend.security.tokenprovider.TokenProvider;
import com.aleksey.eventboardbackend.service.AuthService;
import com.aleksey.eventboardbackend.service.CompanyService;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CompanyService companyService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserDto registerUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new UserAlreadyExistsException(RegisterUserRequest.Fields.email, registerUserRequest.getEmail());
        }
        Company company = null;
        if (registerUserRequest.getCompanyId() != null) {
            company = companyService.getById(registerUserRequest.getCompanyId());
        }

        User user = userMapper.fromRegisterRequestToEntity(registerUserRequest, company);
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public TokenDto loginUser(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getEmail(),
                        loginUserRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.getByEmail(loginUserRequest.getEmail());

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail(), user.getRole());

        return new TokenDto(user.getId(), user.getEmail(), accessToken);
    }
}
