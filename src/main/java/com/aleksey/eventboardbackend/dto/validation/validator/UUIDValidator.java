package com.aleksey.eventboardbackend.dto.validation.validator;

import com.aleksey.eventboardbackend.dto.validation.UUID;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<UUID, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        switch (value) {
            case null -> {
                return true;
            }
            case java.util.UUID uuid -> {
                return true;
            }
            case String string -> {
                try {
                    java.util.UUID.fromString(string);
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }
            default -> {
            }
        }

        return true;
    }
}
