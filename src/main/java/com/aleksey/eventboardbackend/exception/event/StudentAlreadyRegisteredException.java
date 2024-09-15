package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.STUDENT_ALREADY_REGISTERED_MESSAGE;

public class StudentAlreadyRegisteredException extends RuntimeException {
    public StudentAlreadyRegisteredException() {
        super(STUDENT_ALREADY_REGISTERED_MESSAGE);
    }
}
