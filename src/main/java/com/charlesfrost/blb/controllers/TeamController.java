package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.models.ResponseBody;
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
        Team teamToSave = teamService.mapToTeam(teamDTO);
        teamToSave = teamService.save(teamToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody("Suckes!",teamToSave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") Long id) {
        teamService.deleteById(id);
        return ResponseEntity.ok(new ResponseBody("Sukces!",id));
    }

}
