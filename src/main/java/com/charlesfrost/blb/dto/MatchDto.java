package com.charlesfrost.blb.dto;

import org.springframework.stereotype.Service;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MatchDto {
    @DecimalMin("1")
    private Long homeTeamId;
    @DecimalMin("1")
    private Long awayTeamId;
    private int homeTeamScore;
    private int awayTeamScore;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotBlank
    @Size(min = 2, max = 60)
    private String venue;

    public MatchDto(@DecimalMin("1") Long homeTeamId, @DecimalMin("1") Long awayTeamId, int homeTeamScore, int awayTeamScore, @NotNull LocalDate date, @NotNull LocalTime time, @NotBlank @Size(min = 2, max = 60) String venue) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public MatchDto() {
    }

    public MatchDto(@DecimalMin("1") Long homeTeamId, @DecimalMin("1") Long awayTeamId, @NotNull LocalDate date, @NotNull LocalTime time, @NotBlank @Size(min = 2, max = 60) String venue) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }
}