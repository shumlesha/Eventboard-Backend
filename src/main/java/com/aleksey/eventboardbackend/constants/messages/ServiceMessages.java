package com.aleksey.eventboardbackend.constants.messages;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class ServiceMessages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages/servicemessage");

    public static final String USER_ALREADY_EXISTS_MESSAGE = BUNDLE.getString("user.already.exists.message");
    public static final String USER_NOT_FOUND_MESSAGE = BUNDLE.getString("user.not.found.message");
    public static final String USER_NOT_FOUND_BY_ID_MESSAGE = BUNDLE.getString("user.not.found.by.id.message");
    public static final String USER_SUCCESSFULLY_REGISTERED = BUNDLE.getString("user.successfully.registered");
    public static final String USER_SUCCESSFULLY_LOGIN = BUNDLE.getString("user.successfully.login");

    public static final String COMPANY_NOT_FOUND_MESSAGE = BUNDLE.getString("company.not.found.message");
    public static final String COMPANY_ALREADY_EXISTS_MESSAGE = BUNDLE.getString("company.already.exists.message");
    public static final String COMPANY_SUCCESSFULLY_CREATED = BUNDLE.getString("company.successfully.created");
    public static final String COMPANY_SUCCESSFULLY_UPDATED = BUNDLE.getString("company.successfully.updated");
    public static final String COMPANY_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("company.successfully.retrieved");
    public static final String COMPANY_SUCCESSFULLY_DELETED = BUNDLE.getString("company.successfully.deleted");
    public static final String COMPANIES_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("companies.successfully.retrieved");

    public static final String NOT_IN_CHOSEN_COMPANY_MESSAGE = BUNDLE.getString("not.in.chosen.company.message");

    public static final String EVENT_ALREADY_EXISTS_MESSAGE = BUNDLE.getString("event.already.exists.message");
    public static final String EVENT_NOT_FOUND_MESSAGE = BUNDLE.getString("event.not.found.message");
    public static final String EVENT_SUCCESSFULLY_CREATED = BUNDLE.getString("event.successfully.created");
    public static final String EVENT_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("event.successfully.retrieved");
    public static final String EVENT_SUCCESSFULLY_UPDATED = BUNDLE.getString("event.successfully.updated");
    public static final String EVENT_SUCCESSFULLY_DELETED = BUNDLE.getString("event.successfully.deleted");
    public static final String EVENT_SUCCESSFULLY_SIGNED_UP = BUNDLE.getString("event.successfully.signed.up");
    public static final String EVENT_ALREADY_FINISHED_MESSAGE = BUNDLE.getString("event.already.finished.message");
    public static final String EVENT_REGISTRATION_CLOSED_MESSAGE = BUNDLE.getString("event.registration.closed.message");
    public static final String STUDENT_ALREADY_REGISTERED_MESSAGE = BUNDLE.getString("student.already.registered.message");
    public static final String EVENTS_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("events.successfully.retrieved");
    public static final String REGISTRATION_DEADLINE_AFTER_STARTDATE_MESSAGE =
            BUNDLE.getString("registration.deadline.after.startdate.message");
    public static final String CANT_FILTER_BY_SIGNUP_MESSAGE = BUNDLE.getString("cant.filter.by.signup.message");

    public static final String MANAGER_SUCCESSFULLY_CONFIRMED = BUNDLE.getString("manager.successfully.confirmed");
    public static final String MANAGER_ALREADY_CONFIRMED_MESSAGE = BUNDLE.getString("manager.already.confirmed.message");
    public static final String CANT_CONFIRM_MANAGER_MESSAGE = BUNDLE.getString("cant.confirm.manager.message");
    public static final String MANAGERS_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("managers.successfully.retrieved");
    public static final String MANAGER_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("manager.successfully.retrieved");
    public static final String CANT_CONFIRM_SELF_MANAGER_MESSAGE = BUNDLE.getString("cant.confirm.self.manager.message");

    public static final String ACCOUNT_SUCCESSFULLY_RETRIEVED = BUNDLE.getString("account.successfully.retrieved");

    public static final String ACCESS_DENIED_MESSAGE = BUNDLE.getString("access.denied.message");
    public static final String INTERNAL_MESSAGE = BUNDLE.getString("internal.message");
}
