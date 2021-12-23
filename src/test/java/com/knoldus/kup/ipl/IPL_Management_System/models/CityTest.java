package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        city=new City(1L,"kolkata",country);

        venues.add(new Venue(1L,"kolkata stadium",city));
        venues.add(new Venue(2L,"eden stadium",city));
        venues.add(new Venue(3L,"firoz shah kotla",city));
        city.setVenues(venues);

    }
    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId=city.getId();
        assertTrue(expectedId==actualId);
    }



    @Test
    void getCityName() {
        String expectedCity="kolkata";
        String actualCity=city.getCityName();
        assertTrue(expectedCity==actualCity);
    }



    @Test
    void getCountry() {
        String expectedCountry="India";
        String actualCountry=country.getCountryName();
        assertTrue(expectedCountry==actualCountry);
    }

    @Test
    void setId() {
        city.setId(2L);
        Long actualId=city.getId();
        Long expectedId=2L;
        assertTrue(expectedId==actualId);
    }

    @Test
    void setCityName() {

    }

    @Test
    void getVenues() {
        Set<Venue> list=city.getVenues();
        int size=list.size();
        assertTrue(size>1);

    }

    @Test
    void setVenues() {

    }



    @Test
    void setCountry() {
    }


}