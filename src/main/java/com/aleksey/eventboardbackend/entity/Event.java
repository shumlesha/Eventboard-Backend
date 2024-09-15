package com.aleksey.eventboardbackend.entity;

import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private String place;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organizer_id", nullable = false)
    private Company organizer;

    @Column
    private LocalDateTime registrationDeadline;

    private boolean finished = false;

    @ManyToMany
    @JoinTable(
            name = "event_participant",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Student> participants = new HashSet<>();

    public boolean hasManagerAccess(Manager manager) {
        return organizer.containsManager(manager);
    }

    public boolean isRegistrationOpen() {
        return registrationDeadline == null || registrationDeadline.isAfter(LocalDateTime.now());
    }

    public boolean isStudentRegistered(Student student) {
        return participants.contains(student);
    }

    public void addParticipant(Student participant) {
        participants.add(participant);
    }
}
