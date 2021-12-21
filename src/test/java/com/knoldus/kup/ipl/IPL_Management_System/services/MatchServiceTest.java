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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchServiceTest {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    MatchService matchService;

    Venue venue ;
    Team team1;
    Team team2;

    @BeforeEach
    void initializeService(){
        this.matchService = new MatchService(this.matchRepository,this.venueRepository,
                this.teamRepository);
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
        venueRepository.save(venue);
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamRepository.save(team1);
        teamRepository.save(team2);
        Match match1 = new Match(1L,"1/05/2021",venue,team1,team2);
        Match match2 = new Match(2L,"3/05/2021",venue,team1,team2);
        Match match3 = new Match(3L,"4/05/2021",venue,team1,team2);

        matchRepository.save(match1);
        matchRepository.save(match2);
    }

    @Test
    void saveMatch_ReturnTrueIfSaved() {
        Match match = new Match(4L,"5/05/2021",venue,team1,team2);
        matchService.saveMatch(match);
        assertTrue(matchService.getMatchById(match.getId()).isPresent());
    }

    @Test
    void getMatchById_ReturnTrueIfMatchFound() {
        Boolean actual = matchService.getMatchById(2L).isPresent();
        assertTrue(actual);
    }

    @Test
    void getAllMatches_ReturnTrueIfFoundListIsGreaterThan1() {
        int actualSize = matchService.getAllMatches().size();
        assertTrue(actualSize > 1);
    }

    @Test
    void deleteMatch_ReturnFalseIfMatchDeleted() {
        matchService.deleteMatch(1L);
        boolean isExist = matchService.getMatchById(1L).isPresent();
        assertFalse(isExist);
    }

    @Test
    void isSlotBooked_ReturnTrueIfSlotIsAlreadyBooked() {
        Match match = new Match(5L,"1/05/2021",venue,team1,team2);
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
        assertInstanceOf(expected.getClass(), match);
    }
}