package com.aleksey.eventboardbackend.mapper;

import com.aleksey.eventboardbackend.dto.user.RegisterUserRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    Student fromRegisterRequestToStudentEntity(RegisterUserRequest registerUserRequest);

    @Mapping(target = "confirmed", ignore = true)
    @Mapping(target = "id", ignore = true)
    Manager fromRegisterRequestToManagerEntity(RegisterUserRequest registerUserRequest, Company company);

    default User fromRegisterRequestToEntity(RegisterUserRequest registerUserRequest, Company company) {
        if (registerUserRequest.getCompanyId() != null) {
            return fromRegisterRequestToManagerEntity(registerUserRequest, company);
        } else {
            return fromRegisterRequestToStudentEntity(registerUserRequest);
        }
    }

    UserDto toDto(User user);
}
