package com.aleksey.eventboardbackend.mapper;

import com.aleksey.eventboardbackend.dto.company.CompanyDto;
import com.aleksey.eventboardbackend.dto.company.CreateCompanyRequest;
import com.aleksey.eventboardbackend.dto.company.UpdateCompanyRequest;
import com.aleksey.eventboardbackend.dto.event.CreateEventRequest;
import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.dto.event.UpdateEventRequest;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.Event;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.enums.Role;
import com.aleksey.eventboardbackend.security.CurrentUser;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, UserMapper.class})
public interface EventMapper {
    @Mapping(target = "finished", ignore = true)
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizer", source = "company")
    @Mapping(target = "name", expression = "java(createEventRequest.getName().trim())")
    Event toEntity(CreateEventRequest createEventRequest, Company company);

    @Mapping(target = "participants", expression = "java(mapParticipants(event, currentUser))")
    @Mapping(target = "id", source = "event.id")
    EventDto toDto(Event event, @Context CurrentUser currentUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "name", expression = "java(updateEventRequest.getName().trim())")
    void update(@MappingTarget Event event, UpdateEventRequest updateEventRequest);

    default Set<UserDto> mapParticipants(Event event, @Context CurrentUser currentUser) {
        if (currentUser.isStudent()) {
            return Collections.emptySet();
        }

        return event.getParticipants().stream().map(this::mapStudent).collect(Collectors.toSet());
    }

    UserDto mapStudent(Student student);
}
