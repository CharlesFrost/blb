package com.charlesfrost.blb;

import com.charlesfrost.blb.dto.TeamDTO;
import com.charlesfrost.blb.models.*;
import com.charlesfrost.blb.repositories.*;
import com.charlesfrost.blb.services.MatchService;
import com.charlesfrost.blb.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class BlbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlbApplication.class, args);
    }

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamService teamRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StatisticRepostiory statisticRepostiory;

    @Bean
    @Transactional
    CommandLineRunner init() {
        return args -> {
            playerRepository.save(new Player("Karol","Mróz", LocalDate.now().minusYears(22), "łączynik"));
            playerRepository.save(new Player("Czarek","Mikołajczyk", LocalDate.now().minusYears(10),"łączynik"));
            Coach coach = coachRepository.save(new Coach("Karol","Mroz",LocalDate.now().minusYears(11)));
            Coach coach2 = coachRepository.save(new Coach("Karol","Mroz",LocalDate.now().minusYears(11)));
            Team team1 = teamRepository.createTeam(new TeamDTO("Druzyna",LocalDate.now().minusYears(1),coach.getId()));
            Team team2 = teamRepository.createTeam(new TeamDTO("Druzyna",LocalDate.now().minusYears(1),coach2.getId()));
            Team team3 = teamRepository.createTeam(new TeamDTO("Druzyna",LocalDate.now().minusYears(1),coach2.getId()));
            Team team4 = teamRepository.createTeam(new TeamDTO("Druzyna",LocalDate.now().minusYears(1),coach2.getId()));
            matchRepository.save(new Match(team1,team2,1,2, LocalDate.now(),"venue", LocalTime.now().minusHours(1)));
            matchRepository.save(new Match(team2,team1,4,2, LocalDate.now().plusDays(1),"venue", LocalTime.now().minusHours(1)));
            matchRepository.save(new Match(team1,team2,1,2, LocalDate.now(),"venue", LocalTime.now().plusHours(2)));
            User user1= userRepository.save(new User("user",bCryptPasswordEncoder.encode("useruseruser"),"useruseruser","user","user@gmail.com", "ROLE_ADMIN"));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));

            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));
            postRepository.save(new Post(user1,"nazwaposta","SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSScontent",LocalDate.now()));


        };
    }
}
