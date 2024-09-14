package com.aleksey.eventboardbackend.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private UUID id;

    private String email;

    private String accessToken;
}
