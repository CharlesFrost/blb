package com.charlesfrost.blb.services;

import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getOne(Long id) {
        return matchRepository.getOne(id);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }


}
