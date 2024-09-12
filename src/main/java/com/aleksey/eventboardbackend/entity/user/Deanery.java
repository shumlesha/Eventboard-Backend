package com.aleksey.eventboardbackend.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEANERY")
public class Deanery extends User {

}
