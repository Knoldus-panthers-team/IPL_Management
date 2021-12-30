package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.models.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    MatchRepository matchRepository;

    @BeforeEach
    void insertData(){
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);

        Venue venue = new Venue(1L,"Kolkata Stadium",city1);
        venueRepository.save(venue);
        Team team1 = new Team(1L,"KKR", city1);
        Team team2 = new Team(2L,"CSK", city2);
        teamRepository.save(team1);
        teamRepository.save(team2);

        Match match =new Match(1L,"1/05/2021",venue,team1,team2);
        matchRepository.save(match);

    }

    @Test
    void findByVenueId() {
        System.out.println(matchRepository.findByVenueId(1L).get().getVenue());
    }

    @Test
    void findByMatchDate() {
    }

    @Test
    void findByTeam1() {
    }

    @Test
    void findByTeam2() {
    }
}