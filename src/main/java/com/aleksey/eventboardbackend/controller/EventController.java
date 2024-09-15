package com.aleksey.eventboardbackend.controller;

import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.dto.event.EventFilter;
import com.aleksey.eventboardbackend.dto.event.UpdateEventRequest;
import com.aleksey.eventboardbackend.security.CurrentUser;
import com.aleksey.eventboardbackend.service.AccessControlService;
import com.aleksey.eventboardbackend.service.EventService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.aleksey.eventboardbackend.constants.Endpoints.*;
import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.*;
import static com.aleksey.eventboardbackend.constants.messages.SwaggerMessages.*;

@RestController
@RequestMapping(EVENT_URL)
@RequiredArgsConstructor
@Tag(name = EVENT_TAG)
public class EventController {
    private final EventService eventService;

    @PostMapping
    @Operation(summary = EVENTS_CREATE_SUMMARY, description = EVENTS_CREATE_DESCRIPTION)
    @PreAuthorize("@acsi.canCreateEvent(#currentUser.email, #createEventRequest)")
    public ResponseEntity<DefaultResponse<EventDto>> createCompany(@Validated @RequestBody CreateEventRequest createEventRequest,
                                                                   @AuthenticationPrincipal CurrentUser currentUser) {
        EventDto createdEvent = eventService.createEvent(createEventRequest, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(EVENT_SUCCESSFULLY_CREATED, createdEvent.getName()),
                        createdEvent
                )
        );
    }

    @GetMapping(ID)
    @Operation(summary = EVENTS_GET_SUMMARY, description = EVENTS_GET_DESCRIPTION)
    public ResponseEntity<DefaultResponse<EventDto>> getEvent(@PathVariable UUID id,
                                                              @AuthenticationPrincipal CurrentUser currentUser) {
        EventDto event = eventService.getEvent(id, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(EVENT_SUCCESSFULLY_RETRIEVED, event.getId()),
                        event
                )
        );
    }

    @PatchMapping(ID)
    @Operation(summary = EVENTS_UPDATE_SUMMARY, description = EVENTS_UPDATE_DESCRIPTION)
    @PreAuthorize("@acsi.canAccessEvent(#currentUser.email, #id)")
    public ResponseEntity<DefaultResponse<EventDto>> updateEvent(@Validated @RequestBody UpdateEventRequest updateEventRequest,
                                                                 @PathVariable UUID id,
                                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        EventDto updatedEvent = eventService.updateEvent(id, updateEventRequest, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(EVENT_SUCCESSFULLY_UPDATED, updatedEvent.getId()),
                        updatedEvent
                )
        );
    }

    @DeleteMapping(ID)
    @Operation(summary = EVENTS_DELETE_SUMMARY, description = EVENTS_DELETE_DESCRIPTION)
    @PreAuthorize("@acsi.canAccessEvent(#currentUser.email, #id)")
    public ResponseEntity<DefaultResponse<Void>> deleteEvent(@PathVariable UUID id,
                                                              @AuthenticationPrincipal CurrentUser currentUser) {
        eventService.deleteEvent(id, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(EVENT_SUCCESSFULLY_DELETED, id)
                )
        );
    }

    @PostMapping(SIGN_UP)
    @Operation(summary = EVENTS_SIGNUP_SUMMARY, description = EVENTS_SIGNUP_DESCRIPTION)
    @PreAuthorize("#currentUser.isStudent()")
    public ResponseEntity<DefaultResponse<Void>> signUpEvent(@PathVariable UUID id,
                                                             @AuthenticationPrincipal CurrentUser currentUser) {
        eventService.signUpEvent(id, currentUser);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        EVENT_SUCCESSFULLY_SIGNED_UP
                )
        );
    }

    @GetMapping
    @Operation(summary = EVENTS_GETALL_SUMMARY, description = EVENTS_GETALL_DESCRIPTION)
    public ResponseEntity<DefaultResponse<Page<EventDto>>> getAllEvents(EventFilter eventFilter,
                                                                        @AuthenticationPrincipal CurrentUser currentUser,
                                                                        @ParameterObject @PageableDefault(sort = "startDate",
                                                                                direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EventDto> events = eventService.getAllEvents(eventFilter, currentUser, pageable);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        String.format(EVENTS_SUCCESSFULLY_RETRIEVED, events.getTotalElements()),
                        events
                )
        );
    }
}
