package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository repository;

    @Autowired
    CityRepository cityRepository;

//    @Autowired
//    MockMvc mockMvc;

    @Test
    void findByName() {
        City city = new City();
        city.setId(1L);
        cityRepository.save(city);
        Team team = new Team(1L,"KKR", city);
        repository.save(team);
        Boolean actual = Optional.of(repository.findByName(team.getName())).isPresent();
        assertThat(actual).isTrue();
    }
}