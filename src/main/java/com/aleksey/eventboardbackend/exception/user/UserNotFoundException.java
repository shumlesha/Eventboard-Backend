package com.aleksey.eventboardbackend.exception.user;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.USER_NOT_FOUND_MESSAGE;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(String.format(USER_NOT_FOUND_MESSAGE, email));
    }
}
