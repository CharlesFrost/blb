package com.charlesfrost.blb.repositories;

import com.charlesfrost.blb.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    @Query(value = "SELECT * FROM MATCHES WHERE HOME_TEAM_ID=:teamid OR AWAY_TEAM_ID=:teamid",nativeQuery = true)
    List<Match> getMatchesByTeamId(@Param("teamid") Long id);
}
