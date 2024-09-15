package com.aleksey.eventboardbackend.exception.company;

import static com.aleksey.eventboardbackend.constants.messages.ServiceMessages.NOT_IN_CHOSEN_COMPANY_MESSAGE;

public class NotInChosenCompanyException extends RuntimeException {
    public NotInChosenCompanyException() {
        super(String.format(NOT_IN_CHOSEN_COMPANY_MESSAGE));
    }
}
