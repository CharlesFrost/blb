package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/team")
@RestController
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getOne(@PathVariable("id") Long id) {
        Team team = teamService.getOne(id);
        return ResponseEntity.ok(team);
    }

    @PostMapping
    public ResponseEntity<Team> save(@RequestBody @Valid TeamDTO teamDTO) {
        Team teamToSave = teamService.mapToTeam(teamDTO);
        teamToSave = teamService.save(teamToSave);
        return ResponseEntity.ok(teamToSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "id") Long id) {
        teamService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
