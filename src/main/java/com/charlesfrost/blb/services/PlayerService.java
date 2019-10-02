package com.charlesfrost.blb.services;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Player;
import com.charlesfrost.blb.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(Long team_id) {
        return playerRepository.getPlayersByTeamId(team_id);
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player getOne(Long playerId) {
        return playerRepository.getOne(playerId);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono takiego gracza!"));
    }
}
