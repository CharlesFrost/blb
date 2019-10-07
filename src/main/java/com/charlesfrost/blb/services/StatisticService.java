package com.charlesfrost.blb.services;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.repositories.StatisticRepostiory;
import com.charlesfrost.blb.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StatisticService {
    private StatisticRepostiory statisticRepostiory;
    private TeamRepository teamRepository;

    public StatisticService(StatisticRepostiory statisticRepostiory, TeamRepository teamRepository) {
        this.statisticRepostiory = statisticRepostiory;
        this.teamRepository = teamRepository;
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
        List<Team> teams = teamRepository.findAll();
        teams.sort((o1, o2) -> o1.getStatistic().compareTo(o2.getStatistic()));
        for (Team team : teams) {
            try {
                Team nextTeam = teams.get(teams.indexOf(team)+1);
                if (team.getStatistic().getPoints() == nextTeam.getStatistic().getPoints() &&
                        team.getStatistic().getLost() > nextTeam.getStatistic().getLost()) {
                    teams.set(teams.indexOf(nextTeam),team);
                    teams.set(teams.indexOf(team),nextTeam);
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
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
