package com.aleksey.eventboardbackend.dto.validation;

import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default ValidationMessages.PASSWORD_NOT_CORRECT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
