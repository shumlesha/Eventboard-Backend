package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.EVENT_REGISTRATION_CLOSED_MESSAGE;

public class EventRegistrationClosedException extends RuntimeException {
    public EventRegistrationClosedException() {
        super(EVENT_REGISTRATION_CLOSED_MESSAGE);
    }
}
