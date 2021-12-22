package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Country;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Test
    void findByTeamId() {
        City city = new City();
        city.setId(2L);
        cityRepository.save(city);
        Country country = new Country();
        country.setId(1L);
        countryRepository.save(country);
        Team team = new Team(1L,"KKR", city);
        teamRepository.save(team);
        Player player = new Player(1L,"Aasif Ali",team,country,"Batsman");
        playerRepository.save(player);
        Boolean actual = Optional.of(playerRepository.findByTeamId(team.getId())).isPresent();
        assertThat(actual).isTrue();
    }
}