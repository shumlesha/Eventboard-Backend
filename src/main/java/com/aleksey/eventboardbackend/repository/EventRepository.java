package com.aleksey.eventboardbackend.repository;

import com.aleksey.eventboardbackend.dto.event.EventDto;
import com.aleksey.eventboardbackend.entity.Company;
import com.aleksey.eventboardbackend.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    boolean existsByNameAndOrganizerAndFinishedFalse(String name, Company company);

    boolean existsByNameAndIdNotAndOrganizerAndFinishedFalse(String name, UUID id, Company company);

    Page<Event> findAllByOrganizer(Company company, Pageable pageable);

    @Query("""
            SELECT e FROM Event e WHERE
            (:companyId IS NULL OR e.organizer.id = :companyId) AND
            (:signedUp IS NULL OR
            (:signedUp = true AND :studentEmail IN (SELECT s.email FROM e.participants s)) OR
            (:signedUp = false AND :studentEmail NOT IN (SELECT s.email FROM e.participants s)))
            """)
    Page<Event> findAllWithFilters(@Param("companyId") UUID companyId,
                                   @Param("signedUp") Boolean signedUp,
                                   @Param("studentEmail") String studentEmail,
                                   Pageable pageable);

    @Query("""
            SELECT e FROM Event e WHERE
            (:signedUp = true AND :studentEmail IN (SELECT s.email FROM e.participants s)) OR
            (:signedUp = false AND :studentEmail NOT IN (SELECT s.email FROM e.participants s))
            """)
    Page<Event> findAllBySignedUp(@Param("signedUp") Boolean signedUp,
                                  @Param("studentEmail") String studentEmail,
                                  Pageable pageable);
}
