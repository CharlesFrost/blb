package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.ResponseBody;
import com.charlesfrost.blb.models.Statistic;
import com.charlesfrost.blb.services.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    private StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStatistic(@PathVariable(name = "id") Long id, @Valid @RequestBody Statistic statistic) {
        try {
            Statistic returnedStatistic = statisticService.updateStatistic(id,statistic);
            return ResponseEntity.ok(new ResponseBody("Sukces!", returnedStatistic));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseBody("Nie udało się nadpisać danych", statistic));
        }
    }
}
