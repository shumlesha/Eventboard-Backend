package com.aleksey.eventboardbackend.constants.messages;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class ValidationMessages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages/validationmessage");

    public static final String FULLNAME_NOTNULL_REQUIRED = "{fullname.notnull.required}";
    public static final String FULLNAME_NOTBLANK_REQUIRED = "{fullname.notblank.required}";
    public static final String FULLNAME_CONTAINS_ONLY_LETTERS = "{fullname.contains.only.letters}";

    public static final String EMAIL_VALID_REQUIRED = "{email.valid.required}";
    public static final String EMAIL_NOTNULL_REQUIRED = "{email.notnull.required}";

    public static final String PASSWORD_NOTNULL_REQUIRED = "{password.notnull.required}";
    public static final String PASSWORD_LENGTH_CONSTRAINT = "{password.length.constraint}";
    public static final String PASSWORD_RUSSIAN_CONSTRAINT = "{password.russian.constraint}";
    public static final String SPECIAL_SYMBOL_CONSTRAINT = "{special.symbol.constraint}";
    public static final String PASSWORD_NOT_CORRECT = "{password.not.correct}";

    public static final String VALID_UUID_REQUIRED_WITH_NULL = "{valid.uuid.required.with.null}";



    public static final String VALIDATION_FAILED = BUNDLE.getString("validation.failed");
    public static final String NOT_READABLE = BUNDLE.getString("not.readable");
}
