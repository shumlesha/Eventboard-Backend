package com.aleksey.eventboardbackend.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexPatterns {
    public static final String FULLNAME_PATTERN = "^[A-ZА-ЯЁ][a-zа-яё]+\\s[A-ZА-ЯЁ][a-zа-яё]+\\s[A-ZА-ЯЁ][a-zа-яё]+$";

    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static final String RUSSIAN_LETTERS_PATTERN = "[А-Яа-я]";

    public static final String EXCLAMATION_PATTERN = "!";

    public static final String QUESTION_PATTERN = "\\?";

    public static final String COMPANY_NAME_PATTERN = "[A-Za-zА-Яа-я0-9 -]*";

    public static final String EVENT_NAME_PATTERN = "[A-Za-zА-Яа-я0-9 .-]*";

    public static final String EVENT_PLACE_PATTERN = "[A-Za-zА-Яа-я0-9 .-]*";
}
