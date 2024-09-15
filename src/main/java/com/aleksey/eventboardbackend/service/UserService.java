package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    User getByEmail(String email);
    Manager getManagerByEmail(String email);
    Manager getManagerByEmailOrNull(String email);
    Student getStudentByEmail(String email);
    void confirmManager(UUID id, CurrentUser currentUser);

    Page<ManagerDto> getAllManagers(CurrentUser currentUser, Pageable pageable);

    ManagerDto getMyManagerProfile(CurrentUser currentUser);

    UserDto getMyAccount(CurrentUser currentUser);
}
