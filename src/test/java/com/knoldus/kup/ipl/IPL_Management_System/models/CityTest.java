package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    City city;
    Country country;
    Venue venue;
    Set<Venue> venues=new HashSet<>();


    @BeforeEach
    void setUp(){
        country=new Country(1L,"India");
        city=new City(1L,"kolkata",country=new Country(1L,"India"));
        venues.add(new Venue(1L,"kolkata stadium",city));
        venues.add(new Venue(2L,"eden stadium",city));
        venues.add(new Venue(3L,"firoz shah kotla",city));
        city.setVenues(venues);

    }


    @Test
    void testGetCityName() {
        String expectedCity="kolkata";
        String actualCity=city.getCityName();
        assertEquals(expectedCity,actualCity);
    }


    @Test
    void testSetCityName() {
        city.setCityName("Banglore");
        String expectedCity="Banglore";
        String actualCity=city.getCityName();
        assertTrue(expectedCity==actualCity);
    }

    @Test
    void testGetVenues() {
        Set<Venue> list=city.getVenues();
        int size=list.size();
        assertTrue(size>1);
    }

    @Test
    void testSetVenues() {
        venues.add(new Venue(1L,"kolkata stadium",city));
        venues.add(new Venue(2L,"eden stadium",city));
        venues.add(new Venue(3L,"firoz shah kotla",city));
        Set<Venue> list=city.getVenues();
        int size=list.size();
        assertTrue(size>1);
    }

    @Test
    void testGetCountry() {
        String expectedCountry="India";
        String actualCountry= city.getCountry().getCountryName();
        assertTrue(expectedCountry==actualCountry);
    }

    @Test
    void testSetCountry() {
        country=new Country(2L,"England");
        city.setCountry(country);
        String expectedCountry="England";
        String actualCountry=city.getCountry().getCountryName();
        assertTrue(expectedCountry.equals(actualCountry));
    }

    @Test
    void testGetId() {
        Long expectedId=1L;
        Long actualId=city.getId();
        assertTrue(expectedId==actualId);
    }

    @Test
    void testSetId() {
        city.setId(2L);
        Long actualId=city.getId();
        Long expectedId=2L;
        assertEquals(expectedId,actualId);
    }
}