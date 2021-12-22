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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerServiceTest {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CountryRepository countryRepository;

    Team team1,team2;
    Player player1, player2;
    Country country;

    @BeforeEach
    void setUp(){
        this.playerService = new PlayerService(this.playerRepository);
        System.out.println("Started");
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);

        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
        country = new Country(1L,"India");
        countryRepository.save(country);

        player1=new Player(1L,"Rohit Sharma",team1,country,"Batsman");
        player2=new Player(2L,"Virat Kohli",team1,country,"Batsman");
        playerService.savePlayer(player1);
        playerService.savePlayer(player2);

    }

    @Test
    void getNewPlayerObject_CorrectIfReturnNewPlayerObject() {
        Player actual = playerService.getNewPlayerObject();
        assertInstanceOf(Player.class, actual);
    }

    @Test
    void savePlayer_ReturnTrueIfPlayerSaved() {
        Player player = new Player(3L,"Hardik Pandya",team1,country,"Batsman");
        playerService.savePlayer(player);
        assertTrue(playerService.getPlayerById(player.getId()).isPresent());
    }

    @Test
    void getPlayerById_ReturnTrueIfPlayerFound() {
        Boolean actual = playerService.getPlayerById(2L).isPresent();
        assertTrue(actual);
    }

    @Test
    void getPlayersByTeamId_ReturnTrueIfPlayersFoundByTeam() {
        Set<Player> players = playerService.getPlayersByTeamId(1L).get();
        System.out.println(players.size());
        assertTrue(players.size()>1);

    }

    @Test
    void deletePlayer_ReturnFalseIfPlayerIsDeleted() {
        playerService.deletePlayer(1L);
        assertFalse(playerService.getPlayerById(1L).isPresent());
    }
}