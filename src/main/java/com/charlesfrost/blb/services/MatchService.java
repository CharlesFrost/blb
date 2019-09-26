package com.charlesfrost.blb.services;

import com.charlesfrost.blb.dto.MatchDto;
import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.repositories.MatchRepository;
import org.springframework.stereotype.Service;
import sun.util.calendar.LocalGregorianCalendar;

import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private TeamService teamService;

    public MatchService(MatchRepository matchRepository, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
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

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }
}
