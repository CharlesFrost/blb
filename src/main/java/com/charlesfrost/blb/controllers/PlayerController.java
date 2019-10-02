package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Player;
import com.charlesfrost.blb.models.ResponseBody;
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
    public ResponseEntity getOne(@PathVariable("id") Long playerId) {
        Player player = playerService.getOne(playerId);
        if (player==null) {
            return ResponseEntity.status(404).body(new ResponseBody("Nie ma gracza o takim id",player));
        }
        return ResponseEntity.ok(new ResponseBody("Sukces!",player));
    }

    @GetMapping
    public ResponseEntity getPlayers(@RequestParam(name = "team_id", required = false) Long team_id) {
        List<Player> players;
        if (team_id==null) {
            players = playerService.getPlayers();
        } else {
            players = playerService.getPlayers(team_id);
        }

        return ResponseEntity.ok(new ResponseBody("Sukces!",players));
    }

    @PostMapping
    public ResponseEntity savePlayer(@RequestBody @Valid Player player) {
        Player playerToSave = playerService.savePlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody("Sukces!",playerToSave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlayer(@PathVariable(name = "id") Long id) {
        playerService.deleteById(id);
        return ResponseEntity.ok(new ResponseBody("Sukces!",id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayer(@PathVariable(name = "id") Long id, @RequestBody @Valid Player player) {
        try {
            Player playerToUpdate = playerService.findById(id);
            playerToUpdate.setPosition(player.getPosition());
            playerToUpdate.setBirthDate(player.getBirthDate());
            playerToUpdate.setFirstName(player.getFirstName());
            playerToUpdate.setLastName(player.getLastName());
            playerService.savePlayer(playerToUpdate);
            return ResponseEntity.ok(new ResponseBody("Sukces!", playerToUpdate));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseBody("Nie znaleziono takiego gracza", player));
        }
    }
}
