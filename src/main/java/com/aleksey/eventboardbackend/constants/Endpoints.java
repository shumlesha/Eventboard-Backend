package com.aleksey.eventboardbackend.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String BASE_URL = "/api/v1";
    public static final String ID = "/{id}";

    public static final String AUTH_URL = BASE_URL + "/auth";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
}
