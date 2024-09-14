package com.aleksey.eventboardbackend.exception.company;

import java.util.UUID;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.COMPANY_NOT_FOUND_MESSAGE;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(UUID id) {
        super(String.format(COMPANY_NOT_FOUND_MESSAGE, id));
    }
}
