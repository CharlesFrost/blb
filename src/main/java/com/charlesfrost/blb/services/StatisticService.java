package com.charlesfrost.blb.services;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.repositories.StatisticRepostiory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StatisticService {
    private StatisticRepostiory statisticRepostiory;
    private TeamService teamService;

    public StatisticService(StatisticRepostiory statisticRepostiory, TeamService teamService) {
        this.statisticRepostiory = statisticRepostiory;
        this.teamService = teamService;
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

    @Transactional
    public void setTeamsPositions() {
        List<Team> teams = teamService.findAll();
        teams.sort((o1, o2) -> o1.getStatistic().compareTo(o2.getStatistic()));
        Statistic statistic;
        for (Team team : teams) {
            statistic = team.getStatistic();
            statistic.setRankingPosition(teams.indexOf(team)+1);
            statisticRepostiory.save(statistic);
        }
    }

    public Statistic countStatistics(Statistic statistic) {
        statistic.setPlayed(statistic.getLost()+statistic.getWins());
        statistic.setPoints(statistic.getWins()*3);
        return statistic;
    }

}
