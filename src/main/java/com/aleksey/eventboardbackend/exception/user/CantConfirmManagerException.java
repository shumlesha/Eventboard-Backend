package com.aleksey.eventboardbackend.exception.user;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.CANT_CONFIRM_MANAGER_MESSAGE;

public class CantConfirmManagerException extends RuntimeException {
    public CantConfirmManagerException() {
        super(CANT_CONFIRM_MANAGER_MESSAGE);
    }
}
