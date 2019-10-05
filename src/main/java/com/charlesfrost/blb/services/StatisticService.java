package com.charlesfrost.blb.services;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.repositories.StatisticRepostiory;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    private StatisticRepostiory statisticRepostiory;

    public StatisticService(StatisticRepostiory statisticRepostiory) {
        this.statisticRepostiory = statisticRepostiory;
    }

    public Statistic updateStatistic(Long id, Statistic statistic) {
        Statistic statisticToUpdate = statisticRepostiory.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie ma takich statystyk"));
        statisticToUpdate.setLost(statistic.getLost());
        statisticToUpdate.setWins(statistic.getWins());
        statisticRepostiory.save(statisticToUpdate);
        return statisticToUpdate;
    }

    public Statistic findByTeamId(Long id) {
        return statisticRepostiory.findByTeamId(id);
    }

    public Statistic save(Statistic statistic) {
        return statisticRepostiory.save(statistic);
    }
}
