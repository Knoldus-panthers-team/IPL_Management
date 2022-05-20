//package com.knoldus.kup.ipl.repository;
//
//import com.knoldus.kup.ipl.models.City;
//import com.knoldus.kup.ipl.models.Country;
//import com.knoldus.kup.ipl.models.Player;
//import com.knoldus.kup.ipl.models.Team;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//@DataJpaTest
//class PlayerRepositoryTest {
//
//    @MockBean
//    private PlayerRepository playerRepository;
//
//    @Test
//    void findByTeamId() {
//        City city = new City();
//        Country country = new Country();
//        Team team = new Team(1L,"KKR", city);
//        Player player = new Player(1L,"Aasif Ali",team,country,"Batsman");
//        Player player2 = new Player(1L,"Aasif Ali",team,country,"Batsman");
//        Set<Player> list = new HashSet<>();
//        list.add(player);
//        list.add(player2);
//        Mockito.when(playerRepository.findByTeamId(1L)).thenReturn(list);
//        Boolean actual = Optional.of(playerRepository.findByTeamId(team.getId())).isPresent();
//        assertThat(actual).isTrue();
//    }
////    @Test
////    void testFindById(){
////        Country country = new Country(1l,"India");
////        City city = new City(1L,"Roorkee",country);
////
////        Team team = new Team(1L,"KKR", city);
////        Player player = new Player(1L,"Aasif Ali",team,country,"Batsman");
////        Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
////        playerRepository.findById(1L);
////    }
//}