package com.charlesfrost.blb.services;

import com.charlesfrost.blb.dto.MatchDto;
import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.repositories.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private TeamService teamService;
    private StatisticService statisticService;

    public MatchService(MatchRepository matchRepository, TeamService teamService, StatisticService statisticService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.statisticService = statisticService;
    }

    public Match getOne(Long id) {
        return matchRepository.getOne(id);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public List<Match> findUpcomingMatches() {
        return matchRepository.findAll().stream()
                .filter(match -> (match.getDate().isAfter(LocalDate.now())) || (match.getDate().isEqual(LocalDate.now()) && match.getTime().isAfter(LocalTime.now())))
                .collect(Collectors.toList());
    }

    public Match mapToMatch(MatchDto matchDto) {
        Match match = new Match();
        match.setAwayTeam(teamService.getOne(matchDto.getAwayTeamId()));
        match.setHomeTeam(teamService.getOne(matchDto.getHomeTeamId()));
        match.setDate(matchDto.getDate());
        match.setTime(matchDto.getTime());
        match.setVenue(matchDto.getVenue());
        return match;
    }

    @Transactional
    public Match save(Match match) {
        Statistic awayTeamStatistics = statisticService.findByTeamId(match.getAwayTeam().getId());
        Statistic homeTeamStatistics = statisticService.findByTeamId(match.getHomeTeam().getId());
        if (match.getAwayTeamScore() > match.getHomeTeamScore()) {
            awayTeamStatistics.setWins(awayTeamStatistics.getWins()+1);
            homeTeamStatistics.setLost(homeTeamStatistics.getLost()+1);
        } else {
            awayTeamStatistics.setLost(awayTeamStatistics.getLost()+1);
            homeTeamStatistics.setWins(homeTeamStatistics.getWins()+1);
        }
        homeTeamStatistics = statisticService.countStatistics(homeTeamStatistics);
        awayTeamStatistics = statisticService.countStatistics(awayTeamStatistics);
        statisticService.setTeamsPositions();
        statisticService.save(homeTeamStatistics);
        statisticService.save(awayTeamStatistics);

        return matchRepository.save(match);
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }

}
