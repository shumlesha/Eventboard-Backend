package com.aleksey.eventboardbackend.exception.user;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.USER_ALREADY_EXISTS_MESSAGE;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String fieldName, String value) {
        super(String.format(USER_ALREADY_EXISTS_MESSAGE, value, fieldName));
    }
}
