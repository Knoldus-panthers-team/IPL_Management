package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
    @Autowired
    Country country;
    Team team;
    Set<State> states =new HashSet<>();
    Set<Player> players=new HashSet<>();

    @BeforeEach
    void setUp(){
        country=new Country(1L,"India");
        states.add( new State(1L,"kolkata"));
        states.add( new State(2L,"Mumbai"));
        country.setStates(states);

        players.add(new Player(1L,"Rohit",team,country,"Batsman"));
        players.add(new Player(2L,"Sachin",team,country,"Batsman"));

        country.setPlayers(players);
    }

    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId=country.getId();
        assertTrue(actualId==expectedId);
    }

    @Test
    void setId() {
        country.setId(2L);
        Long expectedId=2L;
        Long actualId=country.getId();
        assertTrue(actualId==expectedId);

    }

    @Test
    void getCountryName() {
        String expectedCountryName="India";
        String actualCountryName=country.getCountryName();
        assertTrue(actualCountryName.equals(expectedCountryName));
    }

    @Test
    void setCountryName() {
        country.setCountryName("Australia");
        String expectedCountryName="Australia";
        String actualCountryName=country.getCountryName();
        assertEquals(actualCountryName,expectedCountryName);
    }

    @Test
    void getStates() {
        Set<State> list=country.getStates();
        int size=list.size();
        assertTrue(size>1);
    }

    @Test
    void setStates() {
        states.add( new State(2L,"Chennai"));
        states.add( new State(3L,"Delhi"));
        Set<State> list=country.getStates();
        int size=list.size();
        assertTrue(size>1);
    }

    @Test
    void getPlayers() {
        Set<Player> players=country.getPlayers();
        int size=players.size();
        assertTrue(size>1);

    }

    @Test
    void setPlayers() {
        players.add(new Player(2L,"Sachin Tendulkar",team,country,"Batsman"));
        players.add(new Player(3L,"Rahul",team,country,"Batsman"));
        int size=players.size();
        assertTrue(size>1);
    }
}