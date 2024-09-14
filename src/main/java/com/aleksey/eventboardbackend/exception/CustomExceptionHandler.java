package com.aleksey.eventboardbackend.exception;

import com.aleksey.eventboardbackend.constants.messages.ServiceMessages;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.api.ErrorResponse;
import com.aleksey.eventboardbackend.exception.company.CompanyAlreadyExistsException;
import com.aleksey.eventboardbackend.exception.company.CompanyNotFoundException;
import com.aleksey.eventboardbackend.exception.user.UserAlreadyExistsException;
import com.aleksey.eventboardbackend.exception.user.UserNotFoundException;
import com.aleksey.eventboardbackend.util.ResponseBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExists(UserAlreadyExistsException exception) {
        return ResponseBuilder.error(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException exception) {
        return ResponseBuilder.error(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCompanyNotFound(CompanyNotFoundException exception) {
        return ResponseBuilder.error(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCompanyAlreadyExists(CompanyAlreadyExistsException exception) {
        return ResponseBuilder.error(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenException() {
        return ResponseBuilder.error(
                ServiceMessages.ACCESS_DENIED_MESSAGE,
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        return ResponseBuilder.error(
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return ResponseBuilder.error(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        Map<String, List<String>> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        if (errors.isEmpty()) {
            errors = Map.of(Objects.requireNonNull(e.getBindingResult().getTarget()).getClass().getSimpleName(),
                    e.getBindingResult()
                            .getGlobalErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());
        }

        return ResponseBuilder.error(
                ValidationMessages.VALIDATION_FAILED,
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePropertyReference(PropertyReferenceException exception) {
        return ResponseBuilder.error(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(jakarta.validation.ConstraintViolationException e) {

        Map<String, List<String>> errors = e.getConstraintViolations().stream()
                .collect(Collectors.groupingBy(
                        violation -> violation.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
                ));

        return ResponseBuilder.error(
                ValidationMessages.VALIDATION_FAILED,
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {

        return ResponseBuilder.error(
                ValidationMessages.NOT_READABLE,
                HttpStatus.BAD_REQUEST
        );
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("\nVery sad, because I didn't handle it...\nPlease, check:\n");
        log.error(e.getMessage());
        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseBuilder.error(
                ServiceMessages.INTERNAL_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
