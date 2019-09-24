package com.charlesfrost.blb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "trainers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Coach extends Person implements Serializable {
    public Coach() {
    }

    public Coach(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName, birthDate);
    }


}
