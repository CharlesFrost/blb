package com.charlesfrost.blb.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int wins;
    private int lost;

    public Statistic() {
    }

    public Statistic(int wins, int lost) {
        this.wins = wins;
        this.lost = lost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

}
