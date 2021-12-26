package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    Country country;

    State state;

    @BeforeEach
    void setUp() {
        state = new State(1L,"U P",country=new Country(1L,"India"));
    }
    @Test
    void getStateName() {
        String actualStateName = state.getStateName();
        String expectedStateName = "U P";
        assertEquals(expectedStateName, actualStateName);

    }

    @Test
    void setStateName() {
        state.setStateName("U P");
        String expectedStateName = "U P";
        String actualStateName = state.getStateName();
        assertEquals(expectedStateName, actualStateName);
    }

    @Test
    void getCountry() {
        String actualCountryName = state.getCountry().getCountryName();
        String expectedCountryName = "India";
        assertEquals(actualCountryName, expectedCountryName);
    }

    @Test
    void setCountry() {
        state.setCountry(country);
        country.setCountryName("India");
        String actualCountryName = state.getCountry().getCountryName();
        String expectedCountryName = "India";
        assertTrue(expectedCountryName == actualCountryName);
    }

    @Test
    void getId() {
        Long actualId = state.getId();
        Long expectedId = 1L;
        assertTrue(expectedId==actualId);
    }

    @Test
    void setId() {
        state.setId(1L);
        Long actualId=state.getId();
        Long expectedId = 1L;
        assertTrue(expectedId==actualId);
    }
}