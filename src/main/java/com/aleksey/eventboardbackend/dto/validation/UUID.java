package com.aleksey.eventboardbackend.dto.validation;

import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.validation.validator.UUIDValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
public @interface UUID {
    String message() default ValidationMessages.VALID_UUID_REQUIRED_WITH_NULL;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}