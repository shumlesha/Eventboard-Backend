package com.aleksey.eventboardbackend.dto.event;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private String place;
    private CompanyDto organizer;
    private LocalDateTime registrationDeadline;
    private Set<UserDto> participants;
}
