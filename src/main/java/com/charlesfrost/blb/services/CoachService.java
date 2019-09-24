package com.charlesfrost.blb.services;

import com.charlesfrost.blb.models.Coach;
import com.charlesfrost.blb.repositories.CoachRepository;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    private CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public Coach getOne(Long id) {
        return coachRepository.getOne(id);
    }
}
