package com.aleksey.eventboardbackend.util;

import com.aleksey.eventboardbackend.dto.api.DefaultResponse;
import com.aleksey.eventboardbackend.dto.api.ErrorResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@UtilityClass
public class ResponseBuilder {

    public <T> DefaultResponse<T> success(String message, T data) {
        return DefaultResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    public <T> DefaultResponse<T> success(String message) {
        return success(message, null);
    }

    public ErrorResponse error(String message, HttpStatus status, Map<String, List<String>> errors) {
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }

    public ErrorResponse error(String message, HttpStatus status) {
        return error(message, status, null);
    }

}