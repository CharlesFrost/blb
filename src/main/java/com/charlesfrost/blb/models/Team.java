package com.charlesfrost.blb.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 64, min = 2)
    private String name;
    @NotNull
    private LocalDate createDate;
    @OneToMany
    private Set<Player> players;
    @OneToOne
    @NotNull
    private Coach coach;

    public Team() {
    }

    public Team(@NotBlank @Size(max = 64, min = 2) String name, @NotBlank @NotEmpty LocalDate createDate) {
    this.name = name;
    this.createDate = createDate;
   }

    public Team(@NotBlank @Size(max = 64, min = 2) String name, @NotNull LocalDate createDate, @NotNull Coach coach) {
        this.name = name;
        this.createDate = createDate;
        this.coach = coach;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}