package com.aleksey.eventboardbackend.exception.company;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.COMPANY_ALREADY_EXISTS_MESSAGE;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException(String name) {
        super(String.format(COMPANY_ALREADY_EXISTS_MESSAGE, name));
    }
}
