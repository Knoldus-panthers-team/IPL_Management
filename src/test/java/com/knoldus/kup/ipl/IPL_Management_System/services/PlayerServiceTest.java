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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

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

    Player player1,player2,player3;
    Set<Player> playersList;

    @BeforeEach
    void setUp() {
        this.playerService = new PlayerService(this.playerRepository);
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
        player1 = new Player(1L,"Rohit Sharma",team1,country,"Batsman");
        player2 = new Player(2L,"Virat Kohli",team1,country,"Batsman");
        player3 = new Player(3L,"Virat Kohli",team1,country,"Batsman");
        playersList = new HashSet<>();
        playersList.add(player1);
        playersList.add(player2);
        playersList.add(player3);
    }

    @Test
    void getNewPlayerObject() {
        Player player = new Player();
        assertThat(playerService.getNewPlayerObject().getClass()).isEqualTo(player.getClass());
    }

    @Test
    void savePlayer() {

    }

    @Test
    void getAllPlayers() {
        List<Player> intoList = new ArrayList<>();
        intoList.addAll(playersList);
        Mockito.when(playerRepository.findAll()).thenReturn(intoList);
        assertThat(playerService.getAllPlayers()).isEqualTo(intoList);
        assertTrue(playerService.getAllPlayers().size()>1);
    }

    @Test
    void getPlayerById() {
        Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        assertThat(playerService.getPlayerById(1L)).isEqualTo(player1);
    }

    @Test
    void getPlayersByTeamId() {
        Mockito.when(playerRepository.findByTeamId(1L)).thenReturn(playersList);
        assertThat(playerService.getPlayersByTeamId(1L)).isEqualTo(playersList);
    }

//    @Test
//    void deletePlayer() {
//        playerService.deletePlayer(player1.getId());
//        Mockito.verify(playerRepository, Mockito.times(1))
//                .deleteById(player1.getId());
//    }

//    when(mock.isOk()).thenReturn(true);
//    when(mock.isOk()).thenThrow(exception);
//    doThrow(exception).when(mock).someVoidMethod();
}