package com.aleksey.eventboardbackend.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String BASE_URL = "/api/v1";
    public static final String ID = "/{id}";

    public static final String AUTH_URL = BASE_URL + "/auth";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    public static final String COMPANY_URL = BASE_URL + "/companies";

    public static final String EVENT_URL = BASE_URL + "/events";

    public static final String SIGN_UP = ID + "/sign-up";

    public static final String MANAGERS_URL = BASE_URL + "/managers";

    public static final String CONFIRM = ID + "/confirm";

    public static final String ME = "/me";

    public static final String ACCOUNT_URL = BASE_URL + "/accounts";
}
