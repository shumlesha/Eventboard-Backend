package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.EVENT_ALREADY_EXISTS_MESSAGE;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException(String name) {
        super(String.format(EVENT_ALREADY_EXISTS_MESSAGE, name));
    }
}
