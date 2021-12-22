package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    CityRepository cityRepository;

    Team team1;
    Team team2;

    @BeforeEach
    void setUp(){
        this.teamService = new TeamService(this.teamRepository);

        System.out.println("Started");
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        city3.setId(3L);
        city3.setCityName("Bangalore");
        cityRepository.save(city1);
        cityRepository.save(city2);
        cityRepository.save(city3);

        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
    }

    @Test
    void saveTeam_ReturnTrueIfGivenTeamIsSaved() {
        Team team3 = new Team(3L,"RCB", cityRepository.findById(3L).get());
        teamService.saveTeam(team3);
        assertTrue(teamService.getTeamById(team3.getId()).isPresent());
    }

    @Test
    void getNewTeamObject_CorrectIfReturnNewTeamObject() {
        Team team = teamService.getNewTeamObject();
        assertInstanceOf(Team.class, team);
    }

    @Test
    void getTeamById_ReturnTrueIfTeamFound(){
        Boolean actual = teamService.getTeamById(2L).isPresent();
        assertTrue(actual);
    }

    @Test
    void getAllTeams_ReturnTrueIfReturnTeamList() {
        List<Team> teams = teamService.getAllTeams();
        int actualSize = teams.size();
        assertTrue(actualSize > 1);
    }

    @Test
    void getTeamByName_ReturnTrueIfTeamFound(){
        boolean actual = teamService.getByName("CSK").isPresent();
        assertTrue(actual);
    }

    @Test
    void deleteTeam_CorrectIfReturnFalseOnDeletedTeam() {
        teamService.deleteTeam(1L);
        boolean actualResult = teamService.getTeamById(1L).isPresent();
        assertFalse(actualResult);
    }
}