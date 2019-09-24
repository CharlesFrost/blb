package com.charlesfrost.blb.repositories;

import com.charlesfrost.blb.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Query(value = "SELECT * FROM PLAYERS WHERE ID=(SELECT PLAYERS_ID FROM TEAMS_PLAYERS WHERE TEAM_ID=?1)",
            nativeQuery = true)
    List<Player> getPlayersByTeamId(Long id);
}
