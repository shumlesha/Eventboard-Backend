package com.aleksey.eventboardbackend.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilter {
    @com.aleksey.eventboardbackend.dto.validation.UUID
    private UUID companyId;

    private Boolean signedUp;
}
