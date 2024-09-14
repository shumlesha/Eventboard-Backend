package com.aleksey.eventboardbackend.dto.user;

import com.aleksey.eventboardbackend.constants.RegexPatterns;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = ValidationMessages.FULLNAME_NOTNULL_REQUIRED)
    @NotBlank(message = ValidationMessages.FULLNAME_NOTBLANK_REQUIRED)
    @Pattern(regexp = RegexPatterns.FULLNAME_PATTERN, message = ValidationMessages.FULLNAME_CONTAINS_ONLY_LETTERS)
    private String fullName;

    @Email(regexp = RegexPatterns.EMAIL_PATTERN,
            message = ValidationMessages.EMAIL_VALID_REQUIRED)
    @NotNull(message = ValidationMessages.EMAIL_NOTNULL_REQUIRED)
    private String email;

    @NotNull(message = ValidationMessages.PASSWORD_NOTNULL_REQUIRED)
    @Length(min = 8, max = 64, message = ValidationMessages.PASSWORD_LENGTH_CONSTRAINT)
    @Password
    private String password;

    @com.aleksey.eventboardbackend.dto.validation.UUID
    private UUID companyId;
}
