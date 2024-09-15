package com.aleksey.eventboardbackend.repository;

import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    boolean existsByNameAndOrganizerAndFinishedFalse(String name, Company company);

    boolean existsByNameAndIdNotAndOrganizerAndFinishedFalse(String name, UUID id, Company company);

    Page<Event> findAllByOrganizer(Company company, Pageable pageable);
}
