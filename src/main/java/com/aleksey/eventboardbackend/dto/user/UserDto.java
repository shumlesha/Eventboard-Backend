package com.aleksey.eventboardbackend.dto.user;


import com.aleksey.eventboardbackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String fullName;
    private String email;
    private Role role;
}
