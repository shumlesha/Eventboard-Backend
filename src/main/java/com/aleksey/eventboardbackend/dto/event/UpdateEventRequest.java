package com.aleksey.eventboardbackend.dto.event;

import com.aleksey.eventboardbackend.constants.RegexPatterns;
import com.aleksey.eventboardbackend.constants.messages.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {
    @Pattern(regexp = RegexPatterns.EVENT_NAME_PATTERN, message = ValidationMessages.EVENTNAME_VALID_REQUIRED)
    private String name;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = ValidationMessages.EVENTSTARTDATE_FUTURE_REQUIRED)
    private LocalDateTime startDate;

    @Pattern(regexp = RegexPatterns.EVENT_PLACE_PATTERN, message = ValidationMessages.EVENTPLACE_VALID_REQUIRED)
    private String place;

    @com.aleksey.eventboardbackend.dto.validation.UUID
    private UUID organizerId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = ValidationMessages.EVENTREGISTRATIONDEADLINE_FUTURE_REQUIRED)
    private LocalDateTime registrationDeadline;

    private boolean finished;
}
