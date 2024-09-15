package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.dto.event.EventFilter;
import com.aleksey.eventboardbackend.dto.event.UpdateEventRequest;
import com.aleksey.eventboardbackend.entity.Event;
import com.aleksey.eventboardbackend.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    EventDto createEvent(CreateEventRequest createEventRequest, CurrentUser currentUser);

    EventDto getEvent(UUID id, CurrentUser currentUser);

    Event getById(UUID id);

    EventDto updateEvent(UUID id, UpdateEventRequest updateEventRequest, CurrentUser currentUser);

    void deleteEvent(UUID id, CurrentUser currentUser);

    void signUpEvent(UUID id, CurrentUser currentUser);

    Page<EventDto> getAllEvents(EventFilter eventFilter, CurrentUser currentUser, Pageable pageable);
}
