package com.knoldus.kup.ipl.models;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class VenueTest {

    @Autowired
    Venue venue;
    City city;
    Country country;
    @BeforeEach
    void setUp(){
        venue=new Venue(1L,"Eden Gardens",new City(1L,"Banglore",new Country(1L,"India")));
    }

    @Test
    void getName() {
        String expectedName="Eden Gardens";
        String actualName= venue.getName();
        assertTrue(expectedName.equals(actualName));
    }

    @Test
    void setName() {
        venue.setName("Firoj shah kotla");
        String expectedName= "Firoj shah kotla";
        String actualName= venue.getName();
        assertTrue(expectedName.equals(actualName));
    }

    @Test
    void getCity() {
        String expectedCity="Banglore";
        String actualCity= venue.getCity().getCityName();
        assertTrue(expectedCity.equals(actualCity));
    }

    @Test
    void setCity() {
        city=new City(1L,"Kolkata",country);
        venue.setCity(city);
        String expectedCity="Kolkata";
        String actualCity= venue.getCity().getCityName();
        assertTrue(expectedCity.equals(actualCity));
    }

    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId= venue.getId();
        assertEquals(actualId,expectedId);
    }

    @Test
    void setId() {
        venue.setId(2L);
        Long expectedId=2L;
        Long actualId= venue.getId();
        assertEquals(actualId,expectedId);
    }
}