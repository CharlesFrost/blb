package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.MatchDto;
import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {
    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getUpcomingMatches(@RequestParam(value = "upcoming",required = false, defaultValue = "true") boolean upcoming) {
        List<Match> matches;
        if (upcoming) {
            matches = matchService.findUpcomingMatches();
        } else {
            matches = matchService.findAll();
        }
        return ResponseEntity.ok(matches);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid MatchDto matchDto) {
        Match savedMatch=null;
        try {
            savedMatch = matchService.mapToMatch(matchDto);
            matchService.save(savedMatch);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Wrong team id's");
        }
    }



}
