package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.*;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResultServiceTest {


    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;

    @Autowired
    VenueService venueService;

    @Autowired
    PointService pointService;

    @Autowired
    ResultService resultService;

    @Autowired
    private CityRepository cityRepository;

    Venue venue ;
    Team team1;
    Team team2;
    Match match1,match2;

    @BeforeEach
    void setUp(){
        System.out.println("Started");
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);

        venue = new Venue(1L,"Kolkata Stadium",city1);
        venueService.saveVenue(venue);
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
        match1 = new Match(1L,"1/05/2021",venue,team1,team2);
        match2 = new Match(2L,"2/05/2021",venue,team1,team2);
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
        matchService.saveMatch(match1);
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
        matchService.saveMatch(match1);
        resultService.getResult(match1);
        String expectedResult = "KKR won by 4 wickets";
        String actualResult = matchService.getMatchById(match1.getId()).get().getResult();
        assertEquals(expectedResult,actualResult);
        System.out.println(actualResult);
    }

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
        matchService.saveMatch(match1);
        resultService.getResult(match1);
        pointService.addPointTable(match1);
        List<PointTable> tables = pointService.getAllTables();
        int team1Point = tables.get(0).getPoints();
        int team2Point = tables.get(1).getPoints();
        String actualResult = tables.get(0).getTeam().getName()+": "+team1Point+","+
                tables.get(1).getTeam().getName()+": "+team2Point;
        String expectedResult = "KKR: 2,CSK: 2";
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void addPointTable_ReturnPointTableForBothTeamsWinTeam1() {
        match2.setTossWinnerTeam(team1);
        match2.setTossChoice("batting");
        match2.setTeam1Score("175");
        match2.setTeam2Score("176");
        match2.setTeam1Over("19.2");
        match2.setTeam2Over("20");
        match2.setTeam1Wickets("6");
        match2.setTeam2Wickets("3");
        matchService.saveMatch(match2);
        resultService.getResult(match2);
        pointService.addPointTable(match2);
        List<PointTable> tables = pointService.getAllTables();
        int team1Point = tables.get(0).getPoints();
        int team2Point = tables.get(1).getPoints();
        String actualResult = tables.get(0).getTeam().getName()+": "+team1Point+","+
                tables.get(1).getTeam().getName()+": "+team2Point;
        String expectedResult = "KKR: 0,CSK: 2";
        assertEquals(expectedResult,actualResult);
    }
}