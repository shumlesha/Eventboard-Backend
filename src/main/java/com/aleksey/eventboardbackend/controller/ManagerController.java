package com.aleksey.eventboardbackend.controller;


import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.security.CurrentUser;
import com.aleksey.eventboardbackend.service.UserService;
import com.aleksey.eventboardbackend.util.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.aleksey.eventboardbackend.constants.Endpoints.*;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.*;
import static com.aleksey.eventboardbackend.constants.messages.SwaggerMessages.*;

@RestController
@RequestMapping(MANAGERS_URL)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('DEANERY', 'MANAGER')")
@Tag(name = MANAGERS_TAG)
public class ManagerController {
    private final UserService userService;

    @PostMapping(CONFIRM)
    @Operation(summary = MANAGERS_CONFIRM_SUMMARY, description = MANAGERS_CONFIRM_DESCRIPTION)
    public ResponseEntity<DefaultResponse<Void>> confirmManager(@PathVariable UUID id,
                                                                @AuthenticationPrincipal CurrentUser currentUser) {
        userService.confirmManager(id, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MANAGER_SUCCESSFULLY_CONFIRMED
                )
        );
    }

    @GetMapping
    @Operation(summary = MANAGERS_GETALL_SUMMARY, description = MANAGERS_GETALL_DESCRIPTION)
    public ResponseEntity<DefaultResponse<Page<ManagerDto>>> getAllManagers(@AuthenticationPrincipal CurrentUser currentUser,
                                                                            @ParameterObject @PageableDefault(sort = "fullName",
                                                                                 direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ManagerDto> managers = userService.getAllManagers(currentUser, pageable);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(MANAGERS_SUCCESSFULLY_RETRIEVED, managers.getTotalElements()),
                        managers
                )
        );
    }

    @GetMapping(ME)
    @Operation(summary = MANAGERS_GETMYPROFILE_SUMMARY, description = MANAGERS_GETMYPROFILE_DESCRIPTION)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<DefaultResponse<ManagerDto>> getMyProfile(@AuthenticationPrincipal CurrentUser currentUser) {
        ManagerDto manager = userService.getMyManagerProfile(currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        MANAGER_SUCCESSFULLY_RETRIEVED,
                        manager
                )
        );
    }
}
