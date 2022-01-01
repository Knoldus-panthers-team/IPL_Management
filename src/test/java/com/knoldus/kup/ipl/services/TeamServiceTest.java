package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.CityRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
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
    
    @MockBean
    CityRepository cityRepository;

    @Autowired
    TeamService teamService;

    Team team1;
    Team team2;
    
    List<Team> teamList, fakeList;
    List<City> cityList;

    @BeforeEach
    void setUp(){
//        this.teamService = new TeamService(this.teamRepository);
        System.out.println("Started");
        City city1 = new City(1L, "Kolkata", new Country());
        City city2 = new City(2L, "Chennai", new Country());
        cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);
        
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        fakeList = new ArrayList<>();
        for (int i=0; i<18; i++) {
            fakeList.add(new Team());
        }
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
    
    @Test
    void getAlertOnSave_ReturnSuccessAttributes(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(teamRepository.findAll()).thenReturn(teamList);
        when(teamRepository.save(team1)).thenReturn(team1);
        redirectAttributes = teamService.getAlertOnSave(team1,redirectAttributes);
        String expectedResult = "{message=Team added successfully, messageType=team, alertType=success}";
        assertEquals(expectedResult,String.valueOf(redirectAttributes.getFlashAttributes()));
    }
    @Test
    void getAlertOnSave_ReturnErrorIfTeamGreaterThan15(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(teamRepository.findAll()).thenReturn(fakeList);
        redirectAttributes = teamService.getAlertOnSave(team1,redirectAttributes);
        String expectedResult = "{message=Team can not be more than 15, messageType=team, alertType=error}";
        assertEquals(expectedResult,String.valueOf(redirectAttributes.getFlashAttributes()));
    }
    
    @Test
    void getTeamEditForm_ReturnsEditFormModel(){
        Model model = new ExtendedModelMap();
        when(teamRepository.findById(1L)).thenReturn(Optional.ofNullable(team1));
        when(cityRepository.findAll()).thenReturn(cityList);
        List<City> expectedModel = (List<City>) teamService.getTeamEditForm(model, 1L).getAttribute("cities");
        assertTrue(expectedModel.size()>1);
    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributes(){
        RedirectAttributes actualAttributes = new RedirectAttributesModelMap();
        when(teamRepository.save(team1)).thenReturn(team1);
        actualAttributes = teamService.getAlertOnUpdate(team1,actualAttributes);
        String expectedResult = "{message=Team updated successfully, messageType=team, alertType=success}";
        assertEquals(expectedResult,String.valueOf(actualAttributes.getFlashAttributes()));
    }
    
    @Test
    void getAlertOnDelete_ReturnSuccessAlertAttributes(){
        RedirectAttributes actualAttributes = new RedirectAttributesModelMap();
        actualAttributes = teamService.getAlertOnDelete(1L,actualAttributes);
        String expectedResult = "{message=Team deleted successfully, messageType=team, alertType=success}";
        assertEquals(expectedResult,String.valueOf(actualAttributes.getFlashAttributes()));
    }
    
    @Test
    void getAlertOnNotFound_ReturnErrorAlertAttributes(){
        RedirectAttributes actualAttributes = new RedirectAttributesModelMap();
        actualAttributes = teamService.getAlertOnNotFound(actualAttributes);
        String expectedResult = "{message=Team not found, messageType=team, alertType=error}";
        assertEquals(expectedResult,String.valueOf(actualAttributes.getFlashAttributes()));
    }
}