package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PlayerServiceTest {

    @MockBean
    PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

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

        Team team1, team2;
        Country country;
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);

        country = new Country(1L,"India");
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
        Assertions.assertThat(playerService.getNewPlayerObject().getClass()).isEqualTo(player.getClass());
    }

    @Test
    void savePlayer() {
        Mockito.when(playerRepository.save(player1)).thenReturn(player1);
        Player player = playerService.savePlayer(player1);
        assertNotNull(player);
        assertEquals(player.getTeam(),player1.getTeam());
    }

    @Test
    void getAllPlayers() {
        List<Player> intoList = new ArrayList<>();
        intoList.addAll(playersList);
        Mockito.when(playerRepository.findAll()).thenReturn(intoList);
        Assertions.assertThat(playerService.getAllPlayers()).isEqualTo(intoList);
        assertTrue(playerService.getAllPlayers().size()>1);
    }

    @Test
    void getPlayerById() {
        Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        Assertions.assertThat(playerService.getPlayerById(1L)).isEqualTo(player1);
    }

    @Test
    void getPlayersByTeamId() {
        Mockito.when(playerRepository.findByTeamId(1L)).thenReturn(playersList);
        Assertions.assertThat(playerService.getPlayersByTeamId(1L)).isEqualTo(playersList);
    }

    @Test
    void deletePlayer() {
        playerService.deletePlayer(player1.getId());
        Mockito.verify(playerRepository, Mockito.times(1))
                .deleteById(player1.getId());
    }
}