//package com.knoldus.kup.ipl.IPL_Management_System.services;
//
//import com.knoldus.kup.ipl.IPL_Management_System.models.City;
//import com.knoldus.kup.ipl.IPL_Management_System.models.Country;
//import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
//import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.CountryRepository;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.PlayerRepository;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class PlayerServiceTest1 {
//
//    @Autowired
//    TeamService teamService;
//
//    @Autowired
//    PlayerService playerService;
//
//    @Autowired
//    CityRepository cityRepository;
//
//    @Autowired
//    PlayerRepository playerRepository;
//
//    @Autowired
//    TeamRepository teamRepository;
//
//    @Autowired
//    CountryRepository countryRepository;
//
//    Team team1;
//    Team team2;
//    Country country;
//
//    @BeforeEach
//    void initialization(){
//        this.playerService = new PlayerService(this.playerRepository);
//        City city1 = new City();
//        City city2 = new City();
//        city1.setId(1L);
//        city1.setCityName("Kolkata");
//        city2.setId(2L);
//        city2.setCityName("Chennai");
//        cityRepository.save(city1);
//        cityRepository.save(city2);
//
//        team1 = new Team(1L,"KKR", city1);
//        team2 = new Team(2L,"CSK", city2);
//        teamRepository.save(team1);
//        teamRepository.save(team2);
//
//        country = new Country(1L,"India");
//        countryRepository.save(country);
//
//        Player player1=new Player(1L,"Rohit Sharma",team1,country,"Batsman");
//        Player player2=new Player(2L,"Virat Kohli",team1,country,"Batsman");
//        Player player3=new Player(3L,"Virat Kohli",team1,country,"Batsman");
//
//        playerRepository.save(player1);
//        playerRepository.save(player2);
//        playerRepository.save(player3);
//
//    }
//
//    @Test
//    void getNewPlayerObject_CorrectIfReturnNewPlayerObject() {
//        Player actual = playerService.getNewPlayerObject();
//        assertInstanceOf(Player.class, actual);
//    }
//
//    @Test
//    void savePlayer_ReturnTrueIfPlayerSaved() {
//        Player player = new Player(3L,"Hardik Pandya",team1,country,"Batsman");
//        playerService.savePlayer(player);
//        assertTrue(playerService.getPlayerById(player.getId()).isPresent());
//    }
//
//    @Test
//    void getPlayerById_ReturnTrueIfPlayerFound() {
//        Boolean actual = playerService.getPlayerById(2L).isPresent();
//        assertTrue(actual);
//    }
//
//    @Test
//    void getPlayersByTeamId_ReturnTrueIfPlayersFoundByTeam() {
//        Set<Player> players = playerService.getPlayersByTeamId(1L);
//        System.out.println("list siz:------------- "+players.size());
//        assertTrue(players.size()<1);
//    }
//
//    @Test
//    void deletePlayer_ReturnFalseIfPlayerIsDeleted() {
//        playerService.deletePlayer(1L);
//        assertFalse(playerService.getPlayerById(1L).isPresent());
//    }
//}