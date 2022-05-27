//package com.knoldus.kup.ipl.repository;
//
//import com.knoldus.kup.ipl.models.City;
//import com.knoldus.kup.ipl.models.Team;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//class TeamRepositoryTest {
//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @Autowired
//    CityRepository cityRepository;
//
//    @Test
//    void findByPlayerName_ReturnTrueIfTeamFound() {
//        City city = new City();
//        city.setId(1L);
//        cityRepository.save(city);
//        Team team = new Team(1L,"KKR", city);
//        teamRepository.save(team);
//        Boolean actual = teamRepository.findByName((team.getName())).isPresent();
//        assertThat(actual).isTrue();
//    }
//}