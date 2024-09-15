package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.REGISTRATION_DEADLINE_AFTER_STARTDATE_MESSAGE;

public class RegistrationDeadlineAfterStartDateException extends RuntimeException {
    public RegistrationDeadlineAfterStartDateException() {
        super(REGISTRATION_DEADLINE_AFTER_STARTDATE_MESSAGE);
    }
}
