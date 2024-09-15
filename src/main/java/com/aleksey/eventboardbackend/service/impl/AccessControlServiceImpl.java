package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.entity.Event;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.service.AccessControlService;
import com.aleksey.eventboardbackend.service.EventService;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("acsi")
@RequiredArgsConstructor
public class AccessControlServiceImpl implements AccessControlService {
    private final UserService userService;
    private final EventService eventService;

    @Override
    public boolean canCreateEvent(String email, CreateEventRequest createEventRequest) {
        User user = userService.getByEmail(email);
        return checkManager(user);
    }

    @Override
    public boolean canAccessEvent(String email, UUID id) {
        Manager manager = userService.getManagerByEmailOrNull(email);
        Event event = eventService.getById(id);

        if (manager == null || event == null || event.isFinished()) {
            return false;
        }

        return event.hasManagerAccess(manager);
    }


    private boolean checkManager(User user) {
        if (!user.isManager()) {
            return false;
        }

        return user.isConfirmed();
    }

}
