package com.charlesfrost.blb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player extends Person {

    private String position;

    public Player() {
    }


    public Player(@Size(min = 2, max = 64) @NotBlank String firstName, @Size(min = 2, max = 64) @NotBlank String lastName, @NotNull LocalDate birthDate, String position) {
        super(firstName, lastName, birthDate);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
