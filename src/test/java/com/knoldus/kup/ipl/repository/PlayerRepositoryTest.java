package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlayerRepositoryTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    void findByTeamId() {
        City city = new City();
        Country country = new Country();
        Team team = new Team(1L,"KKR", city);
        Player player = new Player(1L,"Aasif Ali",team,country,"Batsman");
        Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        Boolean actual = Optional.of(playerRepository.findByTeamId(team.getId())).isPresent();
        assertThat(actual).isTrue();
    }
}