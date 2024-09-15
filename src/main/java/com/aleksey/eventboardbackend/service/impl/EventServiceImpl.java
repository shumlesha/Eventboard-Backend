package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.dto.event.EventFilter;
import com.aleksey.eventboardbackend.dto.event.UpdateEventRequest;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.Event;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.exception.company.NotInChosenCompanyException;
import com.aleksey.eventboardbackend.exception.event.*;
import com.aleksey.eventboardbackend.mapper.EventMapper;
import com.aleksey.eventboardbackend.repository.EventRepository;
import com.aleksey.eventboardbackend.security.CurrentUser;
import com.aleksey.eventboardbackend.service.CompanyService;
import com.aleksey.eventboardbackend.service.EventService;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final CompanyService companyService;
    private final EventMapper eventMapper;

    @Override
    @Transactional
    public EventDto createEvent(CreateEventRequest createEventRequest, CurrentUser currentUser) {
        if (createEventRequest.getRegistrationDeadline() != null &&
                createEventRequest.getRegistrationDeadline().isAfter(createEventRequest.getStartDate())) {
            throw new RegistrationDeadlineAfterStartDateException();
        }

        Manager manager = userService.getManagerByEmail(currentUser.getEmail());
        Company company = manager.getCompany();

        if (eventRepository.existsByNameAndOrganizerAndFinishedFalse(createEventRequest.getName().trim(), company)) {
            throw new EventAlreadyExistsException(createEventRequest.getName());
        }

        Event event = eventMapper.toEntity(createEventRequest, company);

        return eventMapper.toDto(eventRepository.save(event), currentUser);
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEvent(UUID id, CurrentUser currentUser) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return eventMapper.toDto(event, currentUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    @Transactional
    public EventDto updateEvent(UUID id, UpdateEventRequest updateEventRequest, CurrentUser currentUser) {
        if (updateEventRequest.getRegistrationDeadline() != null &&
                updateEventRequest.getRegistrationDeadline().isAfter(updateEventRequest.getStartDate())) {
            throw new RegistrationDeadlineAfterStartDateException();
        }

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        if (eventRepository.existsByNameAndIdNotAndOrganizerAndFinishedFalse(
                updateEventRequest.getName().trim(),
                event.getId(),
                event.getOrganizer())) {
            throw new EventAlreadyExistsException(updateEventRequest.getName());
        }

        eventMapper.update(event, updateEventRequest);

        return eventMapper.toDto(eventRepository.save(event), currentUser);
    }

    @Override
    @Transactional
    public void deleteEvent(UUID id, CurrentUser currentUser) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        eventRepository.delete(event);
    }

    @Override
    @Transactional
    public void signUpEvent(UUID id, CurrentUser currentUser) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        Student student = userService.getStudentByEmail(currentUser.getEmail());

        checkStudentRegistration(student, event);
        event.addParticipant(student);

        eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventDto> getAllEvents(EventFilter eventFilter, CurrentUser currentUser, Pageable pageable) {
        if (eventFilter.getCompanyId() != null) {
            Company company = companyService.getById(eventFilter.getCompanyId());

            return eventRepository.findAllByOrganizer(company, pageable).map(event -> eventMapper.toDto(event, currentUser));
        }

        return eventRepository.findAll(pageable).map(event -> eventMapper.toDto(event, currentUser));
    }

    private void checkStudentRegistration(Student student, Event event) {
        if (event.isFinished()) {
            throw new EventAlreadyFinishedException(event.getName());
        }

        if (!event.isRegistrationOpen()) {
            throw new EventRegistrationClosedException();
        }

        if (event.isStudentRegistered(student)) {
            throw new StudentAlreadyRegisteredException();
        }
    }
}
