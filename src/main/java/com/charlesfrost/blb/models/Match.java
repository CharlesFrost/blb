package com.charlesfrost.blb.models;

import com.charlesfrost.blb.validators.ValidateTeams;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "matches")
@ValidateTeams
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;
    @DecimalMin(value = "0")
    private int homeTeamScore;
    @DecimalMin(value = "0")
    private int awayTeamScore;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @Size(max = 64, min = 2)
    @NotBlank
    private String venue;


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, @DecimalMin(value = "0") int homeTeamScore, @DecimalMin(value = "0") int awayTeamScore, @NotNull LocalDate date, @Size(max = 64, min = 2) @NotBlank String venue, @NotNull LocalTime time) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.date = date;
        this.venue = venue;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeTeamScore == match.homeTeamScore &&
                awayTeamScore == match.awayTeamScore &&
                id.equals(match.id) &&
                homeTeam.equals(match.homeTeam) &&
                awayTeam.equals(match.awayTeam) &&
                date.equals(match.date) &&
                time.equals(match.time) &&
                venue.equals(match.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeam, awayTeam, homeTeamScore, awayTeamScore, date, time, venue);
    }
}
