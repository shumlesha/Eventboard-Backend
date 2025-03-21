package com.aleksey.eventboardbackend.dto.validation.validator;


import com.aleksey.eventboardbackend.constants.RegexPatterns;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.validation.Password;
import com.google.common.base.Joiner;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordData;
import org.passay.RuleResult;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        return isPasswordValid(password, context);
    }

    private boolean isPasswordValid(String password, ConstraintValidatorContext context) {
        org.passay.PasswordValidator validator = new org.passay.PasswordValidator(Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
        ));
        RuleResult result = validator.validate(new PasswordData(password));


        boolean containsRussianLetters = Pattern.compile(RegexPatterns.RUSSIAN_LETTERS_PATTERN).matcher(password).find();
        boolean containsExclamation = Pattern.compile(RegexPatterns.EXCLAMATION_PATTERN).matcher(password).find();
        boolean containsQuestion = Pattern.compile(RegexPatterns.QUESTION_PATTERN).matcher(password).find();

        if (result.isValid()) {
            if (containsRussianLetters) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ValidationMessages.PASSWORD_RUSSIAN_CONSTRAINT)
                        .addConstraintViolation();
                return false;
            }
            if (!(containsExclamation || containsQuestion)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ValidationMessages.SPECIAL_SYMBOL_CONSTRAINT)
                        .addConstraintViolation();
                return false;
            }
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}