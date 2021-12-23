package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Country;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CountryRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.PlayerRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PlayerServiceTest {

    @MockBean
    PlayerRepository playerRepository;

    @Mock
    PlayerService playerService;

    @MockBean
    CityRepository cityRepository;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    TeamRepository teamRepository;


    @BeforeEach
    void setUp() {
        this.playerService = new PlayerService(this.playerRepository);
    }

    @Test
    void getNewPlayerObject() {

    }

    @Test
    void savePlayer() {

    }

    @Test
    void getAllPlayers() {
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);

        Team team1, team2;
        Country country;

        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamRepository.save(team1);
        teamRepository.save(team2);

        country = new Country(1L,"India");
        countryRepository.save(country);

        Player player1=new Player(1L,"Rohit Sharma",team1,country,"Batsman");
        Player player2=new Player(2L,"Virat Kohli",team1,country,"Batsman");
        Player player3=new Player(3L,"Virat Kohli",team1,country,"Batsman");

        List<Player> players = new ArrayList<>();
        Mockito.when(playerRepository.findAll()).thenReturn(players);
        assertThat(playerService.getAllPlayers()).isEqualTo(players);

    }

    @Test
    void getPlayerById() {
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);

        Team team1, team2;

        Country country;

        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamRepository.save(team1);
        teamRepository.save(team2);

        country = new Country(1L,"India");
        countryRepository.save(country);

        Player player1=new Player(1L,"Rohit Sharma",team1,country,"Batsman");
//        Player player2=new Player(2L,"Virat Kohli",team1,country,"Batsman");
//        Player player3=new Player(3L,"Virat Kohli",team1,country,"Batsman");

//        playerRepository.save(player1);
//        playerRepository.save(player2);
//        playerRepository.save(player3);

        Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        assertThat(playerService.getPlayerById(1L)).isEqualTo(player1);
    }

    @Test
    void getPlayersByTeamId() {
    }

    @Test
    void deletePlayer() {
    }
}