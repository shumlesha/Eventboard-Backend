package com.aleksey.eventboardbackend.constants.messages;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class ServiceMessages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages/servicemessage");

    public static final String USER_ALREADY_EXISTS_MESSAGE = BUNDLE.getString("user.already.exists.message");
    public static final String USER_NOT_FOUND_MESSAGE = BUNDLE.getString("user.not.found.message");
    public static final String USER_NOT_FOUND_BY_EMAIL_MESSAGE = BUNDLE.getString("user.not.found.by.email.message");
    public static final String USER_SUCCESSFULLY_REGISTERED = BUNDLE.getString("user.successfully.registered");
    public static final String USER_SUCCESSFULLY_LOGIN = BUNDLE.getString("user.successfully.login");

    public static final String COMPANY_NOT_FOUND_MESSAGE = BUNDLE.getString("company.not.found.message");

    public static final String ACCESS_DENIED_MESSAGE = BUNDLE.getString("access.denied.message");
    public static final String INTERNAL_MESSAGE = BUNDLE.getString("internal.message");
}
