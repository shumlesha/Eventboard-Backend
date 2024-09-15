package com.aleksey.eventboardbackend.exception.event;

import java.util.UUID;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.EVENT_NOT_FOUND_MESSAGE;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(UUID id) {
        super(String.format(EVENT_NOT_FOUND_MESSAGE, id));
    }
}
