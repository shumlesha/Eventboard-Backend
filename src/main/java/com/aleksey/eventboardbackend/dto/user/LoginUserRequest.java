package com.aleksey.eventboardbackend.dto.user;


import com.aleksey.eventboardbackend.constants.RegexPatterns;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.aleksey.eventboardbackend.dto.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    @Email(regexp = RegexPatterns.EMAIL_PATTERN,
            message = ValidationMessages.EMAIL_VALID_REQUIRED)
    @NotNull(message = ValidationMessages.EMAIL_NOTNULL_REQUIRED)
    private String email;

    @NotNull(message = ValidationMessages.PASSWORD_NOTNULL_REQUIRED)
    @Length(min = 8, max = 64, message = ValidationMessages.PASSWORD_LENGTH_CONSTRAINT)
    @Password
    private String password;
}
