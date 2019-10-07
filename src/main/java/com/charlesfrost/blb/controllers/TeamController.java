package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.ResponseBody;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    public ResponseEntity findAll() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok(new ResponseBody("Sukces!",teams));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        Team team = teamService.getOne(id);
        if (team == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody("Nie ma gracza o teamu o takim id",team));
        }
        return ResponseEntity.ok(new ResponseBody("Sukces!",team));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid TeamDTO teamDTO) {
        Team teamToSave = teamService.createTeam(teamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody("Suckes!",teamToSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTeam(@PathVariable(name = "id") Long id, @RequestBody @Valid TeamDTO teamDTO) {
        try {
            Team teamToUpdate = teamService.findById(id);
            Team team = teamService.mapToTeam(teamDTO);
            team.setId(teamToUpdate.getId());
            teamService.save(team);
            return ResponseEntity.ok(team);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody("Nie ma takiej drużyny!",teamDTO));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") Long id) {
        teamService.deleteById(id);
        return ResponseEntity.ok(new ResponseBody("Sukces!",id));
    }

}
