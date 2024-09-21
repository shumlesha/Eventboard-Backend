package com.aleksey.eventboardbackend.exception.event;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.CANT_FILTER_BY_SIGNUP_MESSAGE;

public class CantFilterBySignUpException extends RuntimeException {
    public CantFilterBySignUpException() {
        super(CANT_FILTER_BY_SIGNUP_MESSAGE);
    }
}
