package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.*;
import com.knoldus.kup.ipl.IPL_Management_System.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    ResultService resultService;

    Venue venue ;
    Team team1;
    Team team2;
    Match match1,match2;

    @BeforeEach
    void setUp(){
        Country country = new Country(1L,"India");
        City city1 = new City(1L,"Channai",country);
        City city2 = new City(1L,"Kolkata",country);
        venue = new Venue(1L,"Kolkata Stadium",city1);
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        match1 = new Match(1L,"1/05/2021",venue,team1,team2);
        match2 = new Match(2L,"2/05/2021",venue,team1,team2);
        Mockito.when(matchRepository.save(match1)).thenReturn(match1);
        Mockito.when(matchService.getMatchById(1L)).thenReturn(Optional.ofNullable(match1));
//        Mockito.when(pointRepository.findByTeamId());
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

//    @Test
//    void addPointTable_ReturnPointTableForBothTeamsWinTeam2() {
//        match1.setTossWinnerTeam(team2);
//        match1.setTossChoice("batting");
//        match1.setTeam1Score("176");
//        match1.setTeam2Score("175");
//        match1.setTeam1Over("19.2");
//        match1.setTeam2Over("20");
//        match1.setTeam1Wickets("6");
//        match1.setTeam2Wickets("3");
//        resultService.getResult(match1);
//        pointService.addPointTable(match1);
//        List<PointTable> tables = pointService.getAllTables();
//        int team1Point = tables.get(0).getPoints();
//        int team2Point = tables.get(1).getPoints();
//        String actualResult = tables.get(0).getTeam().getName()+": "+team1Point+","+
//                tables.get(1).getTeam().getName()+": "+team2Point;
//        String expectedResult = "KKR: 2,CSK: 2";
//        assertEquals(expectedResult,actualResult);
//    }
//
//    @Test
//    void addPointTable_ReturnPointTableForBothTeamsWinTeam1() {
//        match2.setTossWinnerTeam(team1);
//        match2.setTossChoice("batting");
//        match2.setTeam1Score("175");
//        match2.setTeam2Score("176");
//        match2.setTeam1Over("19.2");
//        match2.setTeam2Over("20");
//        match2.setTeam1Wickets("6");
//        match2.setTeam2Wickets("3");
//        resultService.getResult(match2);
//        pointService.addPointTable(match2);
//        List<PointTable> tables = pointService.getAllTables();
//        int team1Point = tables.get(0).getPoints();
//        int team2Point = tables.get(1).getPoints();
//        String actualResult = tables.get(0).getTeam().getName()+": "+team1Point+","+
//                tables.get(1).getTeam().getName()+": "+team2Point;
//        String expectedResult = "KKR: 0,CSK: 2";
//        assertEquals(expectedResult,actualResult);
//    }
}