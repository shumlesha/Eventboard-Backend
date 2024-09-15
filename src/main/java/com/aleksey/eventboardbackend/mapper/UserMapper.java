package com.aleksey.eventboardbackend.mapper;

import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.dto.user.RegisterUserRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", expression = "java(registerUserRequest.getFullName().trim())")
    Student fromRegisterRequestToStudentEntity(RegisterUserRequest registerUserRequest);

    @Mapping(target = "confirmed", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", expression = "java(registerUserRequest.getFullName().trim())")
    Manager fromRegisterRequestToManagerEntity(RegisterUserRequest registerUserRequest, Company company);

    default User fromRegisterRequestToEntity(RegisterUserRequest registerUserRequest, Company company) {
        if (registerUserRequest.getCompanyId() != null) {
            return fromRegisterRequestToManagerEntity(registerUserRequest, company);
        } else {
            return fromRegisterRequestToStudentEntity(registerUserRequest);
        }
    }

    @Mapping(target = "role", expression = "java(defineRole(user))")
    UserDto toDto(User user);

    @Mapping(target = "companyId", source = "company.id")
    ManagerDto toManagerDto(Manager manager);

    default Role defineRole(User user) {
        return user.getRole();
    }
}
