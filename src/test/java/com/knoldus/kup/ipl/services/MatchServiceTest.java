package com.knoldus.kup.ipl.services;
import com.knoldus.kup.ipl.models.*;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import com.knoldus.kup.ipl.repository.VenueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MatchServiceTest {

    @MockBean
    MatchRepository matchRepository;

    @Autowired
    MatchService matchService;
    
    @MockBean
    TeamRepository teamRepository;
    
    @MockBean
    VenueRepository venueRepository;

    Venue venue1,venue2;
    Team team1;
    Team team2;

    Match match1,match2,match3;

    List<Team> teamList;
    List<Match> matchesList;
    List<Venue> venueList;
    RedirectAttributes redirectAttributes;
    
    @BeforeEach
    void setUp(){
        Country country = new Country(1L,"India");
        City city1 = new City(1L,"Channai",country);
        City city2 = new City(1L,"Kolkata",country);
        venue1 = new Venue(1L,"Kolkata Stadium",city1);
        venue2 = new Venue(1L,"Kolkata Stadium",city1);
    
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        match1= new Match(1L,"1/05/2021",venue1,team1,team2);
        match2 = new Match(2L,"3/05/2021",venue1,team1,team2);
        match3 = new Match(3L,"4/05/2021",venue1,team1,team2);
        matchesList = Arrays.asList(match1,match2,match3);
        Mockito.when(matchRepository.findAll()).thenReturn(matchesList);
        redirectAttributes = new RedirectAttributesModelMap();
        
        teamList = Arrays.asList(team1, team2);
        venueList = Arrays.asList(venue1,venue2);
    }

    @Test
    void saveMatch_ReturnTrueIfSaved() {
        Mockito.when(matchRepository.save(match1)).thenReturn(match1);
        Match savedMatch = matchService.saveMatch(match1);
        assertTrue(savedMatch.equals(match1));
    }

    @Test
    void getMatchById_ReturnTrueIfMatchFound() {
        Mockito.when(matchRepository.findById(2L)).thenReturn(Optional.ofNullable(match2));
        Boolean actual = matchService.getMatchById(2L).isPresent();
        assertTrue(actual);
    }

    @Test
    void getAllMatches_ReturnTrueIfFoundListIsGreaterThan2() {
        int actualSize = matchService.getAllMatches().size();
        assertThat(matchService.getAllMatches()).isEqualTo(matchesList);
        assertTrue(actualSize > 2);
    }

    @Test
    void deleteMatch_ReturnTrueIfMatchDeleted() {
        matchService.deleteMatch(match1.getId());
        Mockito.verify(matchRepository, Mockito.times(1))
                .deleteById(match1.getId());
    }

    @Test
    void isSlotBooked_ReturnTrueIfSlotIsAlreadyBooked() {
        Match match = new Match(4L,"1/05/2021",venue1,team1,team2);
        boolean isBooked = matchService.isSlotBooked(match);
        assertTrue(isBooked);
    }

    @Test
    void isSlotBooked_ReturnFalseIfSlotNotAlreadyBooked() {
        Match match = new Match(5L,"10/05/2021",venue1,team1,team2);
        boolean isBooked = matchService.isSlotBooked(match);
        assertTrue(!isBooked);
    }
    
//    @Test
//    void isSlotBooked_ReturnFalseIfSlotBookedForExistingMatch() {
//        boolean isBooked = matchService.isSlotBooked(match1);
//        assertTrue(!isBooked);
//    }

    @Test
    void isTeamSame_ReturnTrueIfBothTeamSame() {
        Match match = new Match(6L,"8/05/2021",venue1,team1,team1);
        boolean isSame = matchService.isTeamSame(match);
        assertTrue(isSame);
    }

    @Test
    void isTeamSame_ReturnFalseIfBothTeamsNotSame() {
        Match match = new Match(6L,"8/05/2021",venue1,team2,team1);
        boolean isSame = matchService.isTeamSame(match);
        assertTrue(!isSame);
    }

    @Test
    void getNewMatch_ReturnTrueIfReturnNewMatch(){
        Match match = matchService.getNewMatch();
        Match expected = new Match();
        System.out.println(expected.getClass());
    }
    
    @Test
    void getAlertOnSave_ReturnModelWithMessage(){
        Mockito.when(matchRepository.save(match1)).thenReturn(match1);
        RedirectAttributes actualAttributes = matchService.getAlertOnSave(redirectAttributes,match1);
        RedirectAttributes expectedAttributes = new RedirectAttributesModelMap();
        expectedAttributes.addFlashAttribute("message", "Match scheduled successfully");
        expectedAttributes.addFlashAttribute("messageType", "match");
        expectedAttributes.addFlashAttribute("alertType", "success");
        assertThat(actualAttributes).isEqualTo(redirectAttributes);
    }
    
    @Test
    void getMatchDetails_ReturnMatchFormModel(){
        Model model = new ExtendedModelMap();
        Mockito.when(matchRepository.findById(match1.getId())).thenReturn(Optional.ofNullable(match1));
        Mockito.when(teamRepository.findAll()).thenReturn(teamList);
        Mockito.when(venueRepository.findAll()).thenReturn(venueList);
        model = matchService.getMatchDetails(model,match1.getId());
        List<Team> expected = (List<Team>) model.getAttribute("venues");
        assertTrue(expected.size()>1);
    }
    
//    @Test
//    void getAlertOnSave_ReturnSuccessAlertAttributes(){
//        RedirectAttributes attributes = new RedirectAttributesModelMap();
//        attributes = matchService.getAlertOnSave(attributes,match1);
//        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
//        String expectedAttributes = "{message=Match scheduled successfully, messageType=match, alertType=success}";
//        assertEquals(expectedAttributes,actualAttributes);
//    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributes(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        redirectAttributes= matchService.getAlertOnUpdate(redirectAttributes,match1);
        String actualAttributes = String.valueOf(redirectAttributes.getFlashAttributes());
        String expectedAttributes = "{message=Match rescheduled successfully, messageType=match, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertIfSlotBooked_ReturnErrorAlertAttributes(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        redirectAttributes= matchService.getAlertIfSlotBooked(redirectAttributes);
        String actualAttributes = String.valueOf(redirectAttributes.getFlashAttributes());
        String expectedAttributes = "{message=This slot is booked for other match. Please select another date or venue, messageType=match, alertType=error}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertIfTeamSame_ReturnErrorAlertAttributes(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        redirectAttributes= matchService.getAlertIfTeamSame(redirectAttributes);
        String actualAttributes = String.valueOf(redirectAttributes.getFlashAttributes());
        String expectedAttributes = "{message=Teams can't be same, messageType=match, alertType=error}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertOnDelete_ReturnSuccessAlertAttributes(){
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        redirectAttributes= matchService.getAlertOnDelete(redirectAttributes, team1.getId());
        String actualAttributes = String.valueOf(redirectAttributes.getFlashAttributes());
        String expectedAttributes = "{message=Match deleted successfully, messageType=match, alertType=success}";
        assertEquals(expectedAttributes, actualAttributes);
    }
    
    @Test
    void getMatchesWithModel_ReturnMatchesModel(){
        Model model = new ExtendedModelMap();
        Mockito.when(matchRepository.findAll()).thenReturn(matchesList);
        model = matchService.getMatchesWithModel(model);
        List<Team> expected = (List<Team>) model.getAttribute("matchList");
        assertTrue(expected.size()>1);
    }
}