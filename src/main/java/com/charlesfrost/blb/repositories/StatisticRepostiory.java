package com.charlesfrost.blb.repositories;

import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepostiory extends JpaRepository<Statistic, Long> {
    @Query(value = "SELECT * FROM STATISTICS WHERE ID=(SELECT STATISTIC_ID FROM TEAMS WHERE ID=?1)", nativeQuery = true)
    Statistic findByTeamId(Long id);

    Optional<Statistic> findByTeam(Team team);
}
