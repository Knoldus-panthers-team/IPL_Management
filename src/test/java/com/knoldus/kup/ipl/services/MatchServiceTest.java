package com.knoldus.kup.ipl.services;
import com.knoldus.kup.ipl.models.*;
import com.knoldus.kup.ipl.repository.MatchRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MatchServiceTest {

    @MockBean
    MatchRepository matchRepository;

    @Autowired
    MatchService matchService;

    Venue venue;
    Team team1;
    Team team2;

    Match match1,match2,match3;

    List<Match> matchesList;
    RedirectAttributes redirectAttributes;
    
    @BeforeEach
    void setUp(){
        Country country = new Country(1L,"India");
        City city1 = new City(1L,"Channai",country);
        City city2 = new City(1L,"Kolkata",country);
        venue = new Venue(1L,"Kolkata Stadium",city1);
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        match1= new Match(1L,"1/05/2021",venue,team1,team2);
        match2 = new Match(2L,"3/05/2021",venue,team1,team2);
        match3 = new Match(3L,"4/05/2021",venue,team1,team2);
        matchesList = Arrays.asList(match1,match2,match3);
        Mockito.when(matchRepository.findAll()).thenReturn(matchesList);
        redirectAttributes = new RedirectAttributesModelMap();
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
        Match match = new Match(4L,"1/05/2021",venue,team1,team2);
        boolean isBooked = matchService.isSlotBooked(match);
        assertTrue(isBooked);
    }

    @Test
    void isSlotBooked_ReturnFalseIfSlotNotAlreadyBooked() {
        Match match = new Match(5L,"10/05/2021",venue,team1,team2);
        boolean isBooked = matchService.isSlotBooked(match);
        assertTrue(!isBooked);
    }
    
    @Test
    void isSlotBooked_ReturnFalseIfSlotBookedForExistingMatch() {
        boolean isBooked = matchService.isSlotBooked(match1);
        assertTrue(!isBooked);
    }

    @Test
    void isTeamSame_ReturnTrueIfBothTeamSame() {
        Match match = new Match(6L,"8/05/2021",venue,team1,team1);
        boolean isSame = matchService.isTeamSame(match);
        assertTrue(isSame);
    }

    @Test
    void isTeamSame_ReturnFalseIfBothTeamsNotSame() {
        Match match = new Match(6L,"8/05/2021",venue,team2,team1);
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
}