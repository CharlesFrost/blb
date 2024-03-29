package com.charlesfrost.blb.services;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private PlayerService playerService;
    private CoachService coachService;
    private StatisticService statisticService;

    public TeamService(TeamRepository teamRepository, PlayerService playerService, CoachService coachService, StatisticService statisticService) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
        this.coachService = coachService;
        this.statisticService = statisticService;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team getOne(Long id) {
        return teamRepository.getOne(id);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public Team createTeam(TeamDTO teamDTO) {
        Statistic statistic = statisticService.save(new Statistic());
        Team team = mapToTeam(teamDTO);
        team.setStatistic(statistic);
        return teamRepository.save(team);
    }

    public Team mapToTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setCreateDate(teamDTO.getCreateDate());
        team.setCoach(coachService.getOne((teamDTO.getCoachId())));
        return team;
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie ma takiego teamu!"));
    }
}
