package com.aleksey.eventboardbackend.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private UUID id;
    private String fullName;
    private String email;
    private UUID companyId;
    private boolean confirmed;
}
