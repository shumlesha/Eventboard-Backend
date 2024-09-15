package com.aleksey.eventboardbackend.exception.user;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.MANAGER_ALREADY_CONFIRMED_MESSAGE;

public class ManagerAlreadyConfirmedException extends RuntimeException {
    public ManagerAlreadyConfirmedException() {
        super(MANAGER_ALREADY_CONFIRMED_MESSAGE);
    }
}
