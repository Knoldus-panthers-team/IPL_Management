//package com.knoldus.kup.ipl.controllers;
//
//import com.knoldus.kup.ipl.models.City;
//import com.knoldus.kup.ipl.models.Country;
//import com.knoldus.kup.ipl.models.Player;
//import com.knoldus.kup.ipl.models.Team;
//import com.knoldus.kup.ipl.services.PlayerService;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//class PlayerControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PlayerService playerService;
//
//    private Player player1,player2,player3;
//
//    private Set<Player> playerSet;
//
//    @BeforeEach
//    void setUp() {
//        City city1 = new City();
//        City city2 = new City();
//        city1.setId(1L);
//        city1.setCityName("Kolkata");
//        city2.setId(2L);
//        city2.setCityName("Chennai");
//
//        Team team1, team2;
//        Country country;
//        team1 = new Team(1L,"KKR", city1);
////        team2 = new Team(2L,"CSK", city2);
//        country = new Country(1L,"India");
//        player1 = new Player(1L,"Rohit Sharma",team1,country,"Batsman");
//        player2 = new Player(2L,"Virat Kohli",team1,country,"Batsman");
//        player3 = new Player(3L,"Virat Kohli",team1,country,"Batsman");
//    }
//
//    @Test
//    void addForm() {
//    }
//
//    @Test
//    void addPlayer() {
//    }
//
////    @Test
////    void showUpdateForm() {
////        given(studentService.findByStudentNumber(ragcrixStudentNumber)).willReturn(ragcrix);
////
////        mvc.perform(get("/students/byStudentNumber/{studentNumber}", ragcrixStudentNumber)
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.name", is(ragcrix.getName())));
////    }
////    }
//
//    @Test
//    void updatePlayer() {
//    }
//
//    @Test
//    void deletePlayer() {
//    }
//}