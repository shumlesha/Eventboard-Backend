package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.EVENT_ALREADY_FINISHED_MESSAGE;

public class EventAlreadyFinishedException extends RuntimeException {
    public EventAlreadyFinishedException(String name) {
        super(String.format(EVENT_ALREADY_FINISHED_MESSAGE, name));
    }
}
