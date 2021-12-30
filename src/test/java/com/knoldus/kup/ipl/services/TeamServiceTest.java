package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TeamServiceTest {

    @MockBean
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    Team team1;
    Team team2;

    @BeforeEach
    void setUp(){
        this.teamService = new TeamService(this.teamRepository);
        System.out.println("Started");
        City city1 = new City(1L, "Kolkata", new Country());
        City city2 = new City(2L, "Chennai", new Country());
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
    }

    @Test
    void saveTeam_ReturnTrueIfGivenTeamIsSaved() {
        when(teamRepository.save(team1)).thenReturn(team1);
        assertEquals(teamService.saveTeam(team1), team1);
    }

    @Test
    void getNewTeamObject_CorrectIfReturnNewTeamObject() {
        Team team = teamService.getNewTeamObject();
        assertInstanceOf(Team.class, team);
    }

    @Test
    void getTeamById_ReturnTrueIfTeamFound(){
        when(teamRepository.findById(1L)).thenReturn(Optional.ofNullable(team1));
        Boolean actual = teamService.getTeamById(1L).isPresent();
        assertTrue(actual);
    }

    @Test
    void getAllTeams_ReturnTrueIfReturnTeamList() {
        List<Team> teamsList = Arrays.asList(team1,team2);
        Mockito.when(teamRepository.findAll()).thenReturn(teamsList);
        assertThat(teamService.getAllTeams()).isEqualTo(teamsList);
        int actualSize = teamService.getAllTeams().size();
        assertTrue(actualSize > 1);
    }

    @Test
    void getTeamByName_ReturnTrueIfTeamFound(){
        when(teamRepository.findByName("CSK")).thenReturn(Optional.ofNullable(team2));
        assertEquals(teamService.getByName("CSK").get(),team2);
    }

    @Test
    void deleteTeam_CorrectIfReturnFalseOnDeletedTeam() {
        teamService.deleteTeam(1L);
        boolean actualResult = teamService.getTeamById(1L).isPresent();
        assertFalse(actualResult);
    }
}