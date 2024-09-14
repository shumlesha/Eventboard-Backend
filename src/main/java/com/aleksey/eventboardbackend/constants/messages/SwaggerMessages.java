package com.aleksey.eventboardbackend.constants.messages;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class SwaggerMessages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages/swaggermessage");

    public static final String SWAGGER_API_TITLE = BUNDLE.getString("swagger.api.title");
    public static final String SWAGGER_API_DESCRIPTION = BUNDLE.getString("swagger.api.description");
    public static final String API_VERSION_FOR_SWAGGER = BUNDLE.getString("swagger.api.version");

    public static final String AUTH_TAG = "${auth.tag}";
    public static final String AUTH_REGISTER_SUMMARY = "${auth.register.summary}";
    public static final String AUTH_REGISTER_DESCRIPTION = "${auth.register.description}";
    public static final String AUTH_LOGIN_SUMMARY = "${auth.login.summary}";
    public static final String AUTH_LOGIN_DESCRIPTION = "${auth.login.description}";


    public static final String COMPANY_TAG = "${company.tag}";
    public static final String COMPANIES_CREATE_SUMMARY = "${companies.create.summary}";
    public static final String COMPANIES_CREATE_DESCRIPTION = "${companies.create.description}";
    public static final String COMPANIES_UPDATE_SUMMARY = "${companies.update.summary}";
    public static final String COMPANIES_UPDATE_DESCRIPTION = "${companies.update.description}";
    public static final String COMPANIES_GET_SUMMARY = "${companies.get.summary}";
    public static final String COMPANIES_GET_DESCRIPTION = "${companies.get.description}";
    public static final String COMPANIES_DELETE_SUMMARY = "${companies.delete.summary}";
    public static final String COMPANIES_DELETE_DESCRIPTION = "${companies.delete.description}";
    public static final String COMPANIES_GETALL_SUMMARY = "${companies.getall.summary}";
    public static final String COMPANIES_GETALL_DESCRIPTION = "${companies.getall.description}";
}
