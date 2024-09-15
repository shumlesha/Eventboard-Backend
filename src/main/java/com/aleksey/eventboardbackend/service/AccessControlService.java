package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.dto.event.UpdateEventRequest;

import java.util.UUID;

public interface AccessControlService {
    boolean canCreateEvent(String email, CreateEventRequest createEventRequest);

    boolean canAccessEvent(String email, UUID id);
}
