package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.models.Player;
import com.charlesfrost.blb.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getOne(@PathVariable("id") Long playerId) {
        Player player = playerService.getOne(playerId);
        return ResponseEntity.ok(player);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(@RequestParam(name = "team_id", required = false) Long team_id) {
        List<Player> players;
        if (team_id==null) {
            players = playerService.getPlayers();
        } else {
            players = playerService.getPlayers(team_id);
        }
        return ResponseEntity.ok(players);
    }

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {
        Player playerToSave = playerService.savePlayer(player);
        return ResponseEntity.ok(playerToSave);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable(name = "id") Long id) {
        playerService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
