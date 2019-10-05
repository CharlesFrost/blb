package com.charlesfrost.blb.repositories;

import com.charlesfrost.blb.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepostiory extends JpaRepository<Statistic, Long> {
    @Query(value = "SELECT * FROM STATISTICS WHERE ID=(SELECT STATISTIC_ID FROM TEAMS WHERE ID=?1)", nativeQuery = true)
    Statistic findByTeamId(Long id);
}
