package com.charlesfrost.blb;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.models.Coach;
import com.charlesfrost.blb.models.Person;
import com.charlesfrost.blb.models.Player;
import com.charlesfrost.blb.models.Team;
import com.charlesfrost.blb.repositories.CoachRepository;
import com.charlesfrost.blb.repositories.PlayerRepository;
import com.charlesfrost.blb.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@SpringBootApplication
@Configuration
public class BlbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlbApplication.class, args);
    }

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CoachRepository coachRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            playerRepository.save(new Player("Karol","Mróz", LocalDate.now().minusYears(22), "łączynik"));
            playerRepository.save(new Player("Czarek","Mikołajczyk", LocalDate.now().minusYears(10),"łączynik"));
            Coach coach = coachRepository.save(new Coach("Karol","Mroz",LocalDate.now().minusYears(11)));
            teamRepository.save(new Team("Legia",LocalDate.now().minusYears(1),coach));
        };
    }
}
