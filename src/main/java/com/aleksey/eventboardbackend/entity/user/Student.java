package com.aleksey.eventboardbackend.entity.user;

import com.aleksey.eventboardbackend.entity.Event;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    @ManyToMany(mappedBy = "participants")
    private Set<Event> events = new HashSet<>();
}
