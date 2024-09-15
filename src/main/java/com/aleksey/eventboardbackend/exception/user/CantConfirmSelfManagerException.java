package com.aleksey.eventboardbackend.exception.user;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.CANT_CONFIRM_SELF_MANAGER_MESSAGE;

public class CantConfirmSelfManagerException extends RuntimeException {
    public CantConfirmSelfManagerException() {
        super(CANT_CONFIRM_SELF_MANAGER_MESSAGE);
    }
}
