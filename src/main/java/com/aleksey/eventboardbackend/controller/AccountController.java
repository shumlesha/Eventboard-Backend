package com.aleksey.eventboardbackend.controller;

import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.security.CurrentUser;
import com.aleksey.eventboardbackend.service.UserService;
import com.aleksey.eventboardbackend.util.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aleksey.eventboardbackend.constants.Endpoints.*;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.ACCOUNT_SUCCESSFULLY_RETRIEVED;
import static com.aleksey.eventboardbackend.constants.messages.SwaggerMessages.*;

@Slf4j
@RestController
@RequestMapping(ACCOUNT_URL)
@RequiredArgsConstructor
@Tag(name = ACCOUNT_TAG)
public class AccountController {
    private final UserService userService;

    @GetMapping(ME)
    @Operation(summary = ACCOUNTS_GETMYPROFILE_SUMMARY, description = ACCOUNTS_GETMYPROFILE_DESCRIPTION)
    public ResponseEntity<DefaultResponse<UserDto>> getMyAccount(@AuthenticationPrincipal CurrentUser currentUser) {
        UserDto user = userService.getMyAccount(currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ACCOUNT_SUCCESSFULLY_RETRIEVED,
                        user)
        );
    }
}
