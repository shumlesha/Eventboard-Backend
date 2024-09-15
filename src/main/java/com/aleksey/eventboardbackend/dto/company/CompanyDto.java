package com.aleksey.eventboardbackend.dto.company;

import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private UUID id;
    private String name;
    private Set<ManagerDto> managers;
}
