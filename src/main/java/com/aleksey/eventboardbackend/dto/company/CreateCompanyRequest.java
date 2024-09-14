package com.aleksey.eventboardbackend.dto.company;
import com.aleksey.eventboardbackend.constants.RegexPatterns;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyRequest {
    @NotNull(message = ValidationMessages.COMPANYNAME_NOTNULL_REQUIRED)
    @NotBlank(message = ValidationMessages.COMPANYNAME_NOTBLANK_REQUIRED)
    @Pattern(regexp = RegexPatterns.COMPANY_NAME_PATTERN, message = ValidationMessages.COMPANYNAME_VALID_REQUIRED)
    private String name;
}
