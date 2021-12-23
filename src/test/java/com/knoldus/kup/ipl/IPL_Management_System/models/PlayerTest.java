package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
   @Autowired
    Player player;
    Team team;
    Player player1;
    Country country;
    City city;
    @BeforeEach
    void setUp(){
        country=new Country(1L,"India");
        city=new City(1L,"Mumbai",country);
        team=new Team(1L,"MI",city);
        player1=new Player(1L,"Rohit",team,country,"Batsman");
    }

    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId = player1.getId();
        assertTrue(expectedId==actualId);
    }

    @Test
    void getName() {
        String expectedName="Rohit";
        String actualName=player1.getName();
        assertTrue(expectedName==actualName);
    }


    @Test
    void getTeam() {
        String expectedTeam="MI";
        String actualTeam= team.getName();
        assertTrue(actualTeam==expectedTeam);
    }



    @Test
    void getCountry() {
        String expectedCountry="India";
        String actualCountry=country.getCountryName();
        assertTrue(expectedCountry==actualCountry);
    }


    @Test
    void getRole() {
        String expectedRole="Batsman";
        String actualRole=player1.getRole();
        assertTrue(expectedRole==actualRole);
    }

}