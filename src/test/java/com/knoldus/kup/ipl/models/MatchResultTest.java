package com.knoldus.kup.ipl.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {MatchResult.class})
@ExtendWith(SpringExtension.class)
class MatchResultTest {
    @Autowired
    private MatchResult matchResult;
    
    @Test
    void testConstructor() {
        MatchResult actualMatchResult = new MatchResult();
        actualMatchResult.setId(123L);
        actualMatchResult.setMatchWinner("Match Winner");
        actualMatchResult.setResult("Result");
        actualMatchResult.setTeam1("Team1");
        actualMatchResult.setTeam1Over("Team1 Over");
        actualMatchResult.setTeam1Score("Team1 Score");
        actualMatchResult.setTeam1Wickets("Team1 Wickets");
        actualMatchResult.setTeam2("Team2");
        actualMatchResult.setTeam2Over("Team2 Over");
        actualMatchResult.setTeam2Score("Team2 Score");
        actualMatchResult.setTeam2Wickets("Team2 Wickets");
        actualMatchResult.setTossWinnerTeam("Toss Winner Team");
        actualMatchResult.setVenue("Venue");
        assertEquals(123L, actualMatchResult.getId().longValue());
        assertNull(actualMatchResult.getMatchDate());
        assertEquals("Match Winner", actualMatchResult.getMatchWinner());
        assertEquals("Result", actualMatchResult.getResult());
        assertEquals("Team1", actualMatchResult.getTeam1());
        assertEquals("Team1 Score", actualMatchResult.getTeam1Score());
        assertEquals("Team1 Wickets", actualMatchResult.getTeam1Wickets());
        assertEquals("Team2", actualMatchResult.getTeam2());
        assertEquals("Team2 Score", actualMatchResult.getTeam2Score());
        assertEquals("Team2 Wickets", actualMatchResult.getTeam2Wickets());
        assertEquals("Toss Winner Team", actualMatchResult.getTossWinnerTeam());
        assertEquals("Venue", actualMatchResult.getVenue());
    }
    
    @Test
    void testSetMatchDate() {
        this.matchResult.setMatchDate("2020-03-01");
        assertEquals("Yet to be played", this.matchResult.getTeam2Over());
        assertEquals("Yet to be played", this.matchResult.getTeam1Over());
    }
    
    @Test
    void testSetMatchDate2() {
        this.matchResult.setMatchDate("PM");
        assertEquals("PM", this.matchResult.getMatchDate());
    }
    
    @Test
    void testSetMatchDate3() {
        this.matchResult.setMatchDate("AM");
        assertEquals("AM", this.matchResult.getMatchDate());
    }
    
    @Test
    void testGetTeam1Over() {
        assertEquals("Yet to be played", this.matchResult.getTeam1Over());
    }
    
    @Test
    void testGetTeam2Over() {
        assertEquals("Yet to be played", this.matchResult.getTeam2Over());
    }
    
    @Test
    void testGetTeam1FinalScore() {
        assertEquals(" ", this.matchResult.getTeam1FinalScore());
    }
    
    @Test
    void testGetTeam2FinalScore() {
        assertEquals(" ", this.matchResult.getTeam2FinalScore());
    }
}

