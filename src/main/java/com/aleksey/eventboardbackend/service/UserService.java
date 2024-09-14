package com.aleksey.eventboardbackend.service;

import com.aleksey.eventboardbackend.entity.user.User;

public interface UserService {
    User getByEmail(String email);
}
