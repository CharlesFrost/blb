package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.MatchDto;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.models.ResponseBody;
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
    public ResponseEntity getUpcomingMatches(@RequestParam(value = "upcoming",required = false) boolean upcoming) {
        List<Match> matches;
        if (upcoming) {
            matches = matchService.findUpcomingMatches();
        } else {
            matches = matchService.findAll();
        }
        return ResponseEntity.ok(new ResponseBody("Sukces!",matches));
    }

    @GetMapping("/{id}")
    public ResponseEntity getMatch(@PathVariable(name = "id") Long id) {
        Match match = null;
        try {
            match = matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie udało sie znalezc meczu"));
            return ResponseEntity.ok(new ResponseBody("Sukces!",match));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody("Nie ma takiego meczu!", match));
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid MatchDto matchDto) {
        Match savedMatch=null;
        try {
            savedMatch = matchService.mapToMatch(matchDto);
            matchService.save(savedMatch);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody("Sukces!",savedMatch));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody("Nie udało się zapisać meczu!",savedMatch));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateMatch(@PathVariable(name = "id") Long id, @RequestBody @Valid MatchDto matchDto) {
        Match newMatch = null;
        try {
            Match match = matchService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono meczu o id: " + id));
            newMatch = matchService.mapToMatch(matchDto);
            newMatch.setId(match.getId());
            matchService.save(newMatch);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody("Sukces!",newMatch));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody<Match>("Nie ma meczu o takim id",newMatch));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMatch(@PathVariable(name = "id") Long id) {
        matchService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody("Sukces!",id));
    }


}
