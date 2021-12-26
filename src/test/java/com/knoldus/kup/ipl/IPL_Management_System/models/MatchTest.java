package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MatchTest {

    Venue venue;

    Match match;

    City city;

    Team team1;

    Team team2;

    Venue venue1, venue2;

    @BeforeEach
    void setUp() {
        //  Long id, String matchDate, Venue venue, Team team1, Team team2
//        venue = new Venue(1L, "Wankhede Stadium",new City());

        team1 = new Team(1L,"Mumbai Indians",new City());
        team2 = new Team(2L,"Chennai Super Kings",new City());
        venue1 = new Venue(1L, "Wankhede Stadium",new City());
        venue2 = new Venue(2L, "Firoz Shah Kotla",new City());
        match = new Match(1L,"2021-12-16 11:36 AM",
                venue1, team1, team2);

        match.setTeam1Wickets("4");
        match.setTeam2Wickets("10");
        match.setMatchWinner("Mumbai Indians");
        match.setResult("MI won by 36 runs");
        match.setTeam1Score("200");
        match.setTeam2Score("164");
        match.setTeam1Over("20");
        match.setTeam2Over("12");
        match.setTossWinnerTeam(team1);
        match.setTossChoice("batting");
    }

    @Test
    void getId() {
        Long actualId = match.getId();
        Long expectedId = 1L;
        assertTrue(actualId == expectedId);
    }

    @Test
    void setId() {
        match.setId(2L);
        Long actualId = match.getId();
        Long expectedId = 2L;
        assertTrue(actualId == expectedId);
    }

    @Test
    void getMatchDate() {
        String actualMatchDate = match.getMatchDate();
        String expectedMatchDate  = "2021-12-16 11:36 AM";
        assertEquals(actualMatchDate, expectedMatchDate);
    }

    @Test
    void setMatchDate() {
        match.setMatchDate("2021-12-17 11:36 AM");
        String actualMatchDate = match.getMatchDate();
        String expectedMatchDate  = "2021-12-17 11:36 AM";
        assertEquals(actualMatchDate, expectedMatchDate);
    }

    @Test
    void getTeam1Wickets() {
        String actualTeam1Wickets = match.getTeam1Wickets();
        String expectedTeam1wickets = "4";
        assertEquals(actualTeam1Wickets, expectedTeam1wickets);
    }

    @Test
    void setTeam1Wickets() {

        match.setTeam1Wickets("8");
        String actualTeam1Wickets = match.getTeam1Wickets();
        String expectedTeam1wickets = "8";
        assertEquals(actualTeam1Wickets, expectedTeam1wickets);
    }

    @Test
    void getTeam2Wickets() {
        String actualTeam2Wickets = match.getTeam2Wickets();
        String expectedTeam2wickets = "10";
        assertEquals(actualTeam2Wickets, expectedTeam2wickets);
    }

    @Test
    void setTeam2Wickets() {
        match.setTeam2Wickets("6");
        String actualTeam2Wickets = match.getTeam2Wickets();
        String expectedTeam2wickets = "6";
        assertEquals(actualTeam2Wickets, expectedTeam2wickets);
    }

    @Test
    void getMatchWinner() {
        String actualMatchWinner = match.getMatchWinner();
        String expectedMatchWinner = "Mumbai Indians";
        assertEquals(expectedMatchWinner, actualMatchWinner);
    }

    @Test
    void setMatchWinner() {
        match.setMatchWinner("CSK");
        String actualMatchWinner = match.getMatchWinner();
        String expectedMatchWinner = "CSK";
        assertEquals(actualMatchWinner, expectedMatchWinner);
    }

    @Test
    void getResult() {
        String actualResult = match.getResult();
        String expectedResult = "MI won by 36 runs";
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void setResult() {
        match.setResult("CSK won by 4 wickets");
        String actualResult = match.getResult();
        String expectedResult = "CSK won by 4 wickets";
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void getTeam1Score() {
        String actualTeam1Score = match.getTeam1Score();
        String expectedTeam1Score = "200";
        assertEquals(actualTeam1Score, expectedTeam1Score);

    }

    @Test
    void setTeam1Score() {
        match.setTeam1Score("148");
        String actualTeam1Score = match.getTeam1Score();
        String expectedTeam1Score = "148";
        assertEquals(actualTeam1Score, expectedTeam1Score);
    }

    @Test
    void getTeam2Score() {
        String actualTeam2Score = match.getTeam2Score();
        String expectedTeam2Score = "164";
        assertEquals(actualTeam2Score, expectedTeam2Score);
    }

    @Test
    void setTeam2Score() {
        match.setTeam2Score("216");
        String actualTeam2Score = match.getTeam2Score();
        String expectedTeam2Score = "216";
        assertEquals(actualTeam2Score, expectedTeam2Score);
    }

    @Test
    void getTeam1Over() {
        String actualTeam1Over = match.getTeam1Over();
        String expectedTeam1Over = "20";
        assertEquals(actualTeam1Over, expectedTeam1Over);
    }

    @Test
    void setTeam1Over() {
        match.setTeam1Over("18");
        String actualTeam1Over = match.getTeam1Over();
        String expectedTeam1Over = "18";
        assertEquals(actualTeam1Over, expectedTeam1Over);
    }

    @Test
    void getTeam2Over() {
        String actualTeam2Over = match.getTeam2Over();
        String expectedTeam2Over = "12";
        assertEquals(actualTeam2Over, expectedTeam2Over);
    }

    @Test
    void setTeam2Over() {
        match.setTeam2Over("16");
        String actualTeam2Over = match.getTeam2Over();
        String expectedTeam2Over = "16";
        assertEquals(actualTeam2Over, expectedTeam2Over);
    }

    @Test
    void getVenue() {
        String actualVenue = match.getVenue().getName();
        String expectedVenue = "Wankhede Stadium";
        assertEquals(actualVenue, expectedVenue);
    }

    @Test
    void setVenue() {
        match.setVenue(venue2);
        String actualVenue = match.getVenue().getName();
        String expectedVenue = "Firoz Shah Kotla";
        assertEquals(actualVenue, expectedVenue);
    }

    @Test
    void getTeam1() {
        String actualTeam1 = match.getTeam1().getName();
        String expectedTeam1 = "Mumbai Indians";
        assertEquals(actualTeam1, expectedTeam1);
    }

    @Test
    void setTeam1() {
        match.setTeam1(team1);
        team1.setName("Rajesthan Royals");
        String actualTeam1 = match.getTeam1().getName();
        String expectedTeam1 = "Rajesthan Royals";
        assertEquals(actualTeam1, expectedTeam1);
    }

    @Test
    void getTeam2() {
        String actualTeam2 = match.getTeam2().getName();
        String expectedTeam2 = "Chennai Super Kings";
        assertEquals(actualTeam2, expectedTeam2);
    }

    @Test
    void setTeam2() {
        match.setTeam2(team2);
        team2.setName("Sunrizers Hydrabad");
        String actualTeam2 = match.getTeam2().getName();
        String expectedTeam2 = "Sunrizers Hydrabad";
        assertEquals(actualTeam2, expectedTeam2);
    }

    @Test
    void getTossWinnerTeam() {
        String actualTossWinnerTeam = match.getTossWinnerTeam().getName();
        String expectedTossWinnerTeam = "Mumbai Indians";
        assertEquals(actualTossWinnerTeam, expectedTossWinnerTeam);
    }

    @Test
    void setTossWinnerTeam() {
        match.setTossWinnerTeam(team2);
        match.getTeam1().setName("Rajesthan Royals");
        String actualTossWinnerTeam = match.getTeam1().getName();
        String expectedTossWinnerTeam = "Rajesthan Royals";
        assertEquals(actualTossWinnerTeam, expectedTossWinnerTeam);
    }

    @Test
    void getTossChoice() {
        String actualTossChoice = match.getTossChoice();
        String expectedTossChoice = "batting";
        assertEquals(actualTossChoice, expectedTossChoice);

    }

    @Test
    void setTossChoice() {
        match.setTossChoice("bowling");
        String actualTossChoice = match.getTossChoice();
        String expectedTossChoice = "bowling";
        assertEquals(actualTossChoice, expectedTossChoice);
    }


    @Test
    void getTeam1FinalScore() {
        String actualTeam1FinalScore = match.getTeam1FinalScore();
        String expectedTeam1FinalScore = "200/4";
        assertEquals(expectedTeam1FinalScore, actualTeam1FinalScore);
    }

    @Test
    void getTeam2FinalScore() {
        String actualTeam2FinalScore = match.getTeam2FinalScore();
        String expectedTeam2FinalScore = "164/10";
        assertEquals(expectedTeam2FinalScore, actualTeam2FinalScore);
    }
}