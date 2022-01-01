package com.knoldus.kup.ipl.services;
import com.knoldus.kup.ipl.models.*;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.repository.PointRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import com.knoldus.kup.ipl.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ResultServiceTest {

    @Autowired
    MatchService matchService;

    @Autowired
    PointService pointService;

    @MockBean
    MatchRepository matchRepository;

    @MockBean
    PointRepository pointRepository;
    
    @MockBean
    TeamRepository teamRepository;
    
    @MockBean
    VenueRepository venueRepository;

    @Autowired
    ResultService resultService;

    Venue venue1, venue2 ;
    Team team1;
    Team team2;
    Match match1,match2;
    List<PointTable> tables;
    List<Team> teamList;
    List<Venue> venueList;
    PointTable pointTable1,pointTable2;

    @BeforeEach
    void setUp(){
        Country country = new Country(1L,"India");
        City city1 = new City(1L,"Channai",country);
        City city2 = new City(1L,"Kolkata",country);
        venue1 = new Venue(1L,"Kolkata Stadium",city1);
        venue2 = new Venue(2L,"Firoz Shah Kotla",city1);
    
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        match1 = new Match(1L,"1/05/2021",venue1,team1,team2);
        match2 = new Match(2L,"2/05/2021",venue2,team1,team2);
        Mockito.when(matchRepository.save(match1)).thenReturn(match1);
        Mockito.when(matchService.getMatchById(1L)).thenReturn(Optional.ofNullable(match1));
        pointTable1 = new PointTable(1L,1,team1,1,1,2,0.417);
        teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        venueList = Arrays.asList(venue1,venue2);
    
        match1.setTossChoice("bowling");
        match1.setTeam1Score("175");
        match1.setTeam2Score("176");
        match1.setTeam1Over("19.2");
        match1.setTeam2Over("19");
        match1.setTeam1Wickets("10");
        match1.setTeam2Wickets("3");
        match1.setTossWinnerTeam(team1);
        match1.setTossChoice("batting");
    }

    @Test
    void getResult_ReturnResultWonByRunsIfTeamWonByRuns() {
        match1.setTossWinnerTeam(team1);
        match1.setTossChoice("bowling");
        match1.setTeam1Score("175");
        match1.setTeam2Score("176");
        match1.setTeam1Over("19.2");
        match1.setTeam2Over("19");
        match1.setTeam1Wickets("10");
        match1.setTeam2Wickets("3");
        resultService.getResult(match1);
        String expectedResult = "CSK won by 1 runs";
        String actualResult = matchService.getMatchById(match1.getId()).get().getResult();
        assertEquals(expectedResult,actualResult);
        System.out.println(actualResult);
    }

    @Test
    void getResult_ReturnResultWonByWicketsIfTeamWonByWickets() {
        match1.setTossWinnerTeam(team2);
        match1.setTossChoice("batting");
        match1.setTeam1Score("176");
        match1.setTeam2Score("175");
        match1.setTeam1Over("19.2");
        match1.setTeam2Over("20");
        match1.setTeam1Wickets("6");
        match1.setTeam2Wickets("3");
        resultService.getResult(match1);
        String expectedResult = "KKR won by 4 wickets";
        String actualResult = matchService.getMatchById(match1.getId()).get().getResult();
        assertEquals(expectedResult,actualResult);
        System.out.println(actualResult);
    }

//    Pending for PointTable test

    @Test
    void addPointTable_ReturnPointTableForBothTeamsWinTeam2() {
        match1.setTossWinnerTeam(team2);
        match1.setTossChoice("batting");
        match1.setTeam1Score("176");
        match1.setTeam2Score("175");
        match1.setTeam1Over("19.2");
        match1.setTeam2Over("20");
        match1.setTeam1Wickets("6");
        match1.setTeam2Wickets("3");
        pointTable1 = new PointTable(1L,1,team1,1,1,2,0.417);
        pointTable2 = new PointTable(2L,1,team2,0,1,0,-0.417);
        List<PointTable> tableList = Arrays.asList(pointTable1,pointTable2);
        Mockito.when(pointRepository.save(pointTable1)).thenReturn(pointTable1);
        Mockito.when(pointRepository.findAll()).thenReturn(tableList);
        List<PointTable> outputtables = pointService.getAllTables();
        resultService.getResult(match1);
        pointService.addPointTable(match1);
        int team1Point = outputtables.get(0).getPoints();
        int team2Point = outputtables.get(1).getPoints();
        String actualResult = outputtables.get(0).getTeam().getName()+": "+team1Point+","+
                outputtables.get(1).getTeam().getName()+": "+team2Point;
        String expectedResult = "KKR: 2,CSK: 0";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void addPointTable_ReturnPointTableForBothTeamsWinTeam1() {
        match2.setTossWinnerTeam(team1);
        match2.setTossChoice("batting");
        match2.setTeam1Score("175");
        match2.setTeam2Score("176");
        match2.setTeam1Over("20");
        match2.setTeam2Over("19.2");
        match2.setTeam1Wickets("6");
        match2.setTeam2Wickets("3");
        PointTable pointTable1 = new PointTable(1L,1,team1,0,1,0,-0.417);
        PointTable pointTable2 = new PointTable(2L,1,team2,1,1,2,0.417);

        List<PointTable> tableList = Arrays.asList(pointTable1,pointTable2);
        Mockito.when(pointRepository.save(pointTable1)).thenReturn(pointTable1);
        Mockito.when(pointRepository.findAll()).thenReturn(tableList);
        List<PointTable> outputtables = pointService.getAllTables();
        resultService.getResult(match2);
        pointService.addPointTable(match2);
        int team1Point = outputtables.get(0).getPoints();
        int team2Point = outputtables.get(1).getPoints();
        String actualResult = outputtables.get(0).getTeam().getName()+": "+team1Point+","+
                outputtables.get(1).getTeam().getName()+": "+team2Point;
        String expectedResult = "KKR: 0,CSK: 2";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getById_ReturnTrueIfPointTableFoundById(){
        Mockito.when(pointRepository.findById(1L)).thenReturn(Optional.ofNullable(pointTable1));
        assertThat(pointService.getById(1L)).isEqualTo(pointTable1);
    }
    
    @Test
    void getScoreForm_ReturnScoreFormModel(){
        Model model = new ExtendedModelMap();
        Mockito.when(matchRepository.findById(match1.getId())).thenReturn(Optional.ofNullable(match1));
        Mockito.when(teamRepository.findAll()).thenReturn(teamList);
        Mockito.when(venueRepository.findAll()).thenReturn(venueList);
        model = resultService.getScoreForm(model,match1.getId());
        List<Team> expected = (List<Team>) model.getAttribute("teams");
        assertTrue(expected.size()>1);
    }
    
    @Test
    void getScoreForm_ReturnScoreFormModelOnEmpty(){
        Model model = new ExtendedModelMap();
        Mockito.when(matchRepository.findById(match1.getId())).thenReturn(Optional.ofNullable(match1));
        Mockito.when(teamRepository.findAll()).thenReturn(teamList);
        Mockito.when(venueRepository.findAll()).thenReturn(venueList);
        match1.setTeam1Over("Yet to be played");
        match1.setTeam2Over("Yet to be played");
        model = resultService.getScoreForm(model,match1.getId());
        List<Team> expected = (List<Team>) model.getAttribute("teams");
        assertTrue(expected.size()>1);
    }
    
    @Test
    void getEditScoreForm_ReturnEditFormModel(){
        Model model = new ExtendedModelMap();
        Mockito.when(matchRepository.findById(match1.getId())).thenReturn(Optional.ofNullable(match1));
        Mockito.when(teamRepository.findAll()).thenReturn(teamList);
        Mockito.when(venueRepository.findAll()).thenReturn(venueList);
        model = resultService.getEditScoreForm(match1.getId(),model);
        List<Team> expected = (List<Team>) model.getAttribute("venues");
        assertTrue(expected.size()>1);
    }
    
    @Test
    void getAlertOnSave_ReturnSuccessAlertAttributes(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        attributes = resultService.getAlertOnSave(match1,attributes);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Score added successfully, messageType=score, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributes(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        pointTable1 = new PointTable(1L,4,team1,3,1,6,0.417);
        pointTable2 = new PointTable(2L,4,team1,3,1,6,0.417);
        Mockito.when(pointRepository.findByTeamId(1L)).thenReturn(pointTable1);
        Mockito.when(pointRepository.findByTeamId(2L)).thenReturn(pointTable2);
        attributes = resultService.getAlertOnUpdate(attributes,match1);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Score updated successfully, messageType=score, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributesIfTeam1WinsByRuns(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        pointTable1 = new PointTable(1L,4,team1,3,1,6,0.417);
        pointTable2 = new PointTable(2L,4,team1,3,1,6,0.417);
        Mockito.when(pointRepository.findByTeamId(1L)).thenReturn(pointTable1);
        Mockito.when(pointRepository.findByTeamId(2L)).thenReturn(pointTable2);
        match1.setTeam1Score("177");
        attributes = resultService.getAlertOnUpdate(attributes,match1);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Score updated successfully, messageType=score, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributesIfTeam1WinsByWickets(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        pointTable1 = new PointTable(1L,4,team1,3,1,6,0.417);
        pointTable2 = new PointTable(2L,4,team1,3,1,6,0.417);
        Mockito.when(pointRepository.findByTeamId(1L)).thenReturn(pointTable1);
        Mockito.when(pointRepository.findByTeamId(2L)).thenReturn(pointTable2);
        match1.setTossChoice("bowling");
        match1.setTeam1Score("177");
        attributes = resultService.getAlertOnUpdate(attributes,match1);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Score updated successfully, messageType=score, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
}