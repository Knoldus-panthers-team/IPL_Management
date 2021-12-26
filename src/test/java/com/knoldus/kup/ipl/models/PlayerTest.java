package com.knoldus.kup.ipl.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Autowired
    Team team;
    Player player1;
    Country country;
    City city;

    @BeforeEach
    void setUp(){
        city=new City(1L,"Mumbai",country);
        player1=new Player(1L,"Rohit",team=new Team(1L,"MI",city),country=new Country(1L,"India"),"Batsman");
    }

    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId = player1.getId();
        assertEquals(expectedId,actualId);
    }
    @Test
    void setId() {
        player1.setId(2L);
        Long expectedId=2L;
        Long actualId = player1.getId();
        assertEquals(expectedId,actualId);
    }

    @Test
    void getName() {
        String expectedName="Rohit";
        String actualName=player1.getName();
        assertEquals(expectedName,actualName);
    }

    @Test
    void setName() {
        player1.setName("Sachin");
        String expectedName="Sachin";
        String actualName=player1.getName();
        assertEquals(expectedName,actualName);
    }

    @Test
    void getTeam() {
        String expectedTeamName="MI";
        String actualTeamName= player1.getTeam().getName();
        assertTrue(actualTeamName.equals(expectedTeamName));
    }

    @Test
    void setTeam() {
        player1.setTeam(team);
        team.setName("RCB");
        String expectedTeamName="RCB";
        String actualTeamName= player1.getTeam().getName();
        assertTrue(actualTeamName.equals(expectedTeamName));
    }

    @Test
    void getCountry() {
        String expectedCountry="India";
        String actualCountry=player1.getCountry().getCountryName();
        assertTrue(expectedCountry.equals(actualCountry));
    }

    @Test
    void setCountry() {
        player1.setCountry(country);
        String expectedCountry="India";
        String actualCountry=player1.getCountry().getCountryName();
        assertTrue(expectedCountry.equals(actualCountry));
    }

    @Test
    void getRole() {
        String expectedRole="Batsman";
        String actualRole=player1.getRole();
        assertTrue(expectedRole.equals(actualRole));
    }

    @Test
    void setRole() {
        player1.setRole("Bowler");
        String expectedRole="Bowler";
        String actualRole=player1.getRole();
        assertTrue(expectedRole.equals(actualRole));
    }
}