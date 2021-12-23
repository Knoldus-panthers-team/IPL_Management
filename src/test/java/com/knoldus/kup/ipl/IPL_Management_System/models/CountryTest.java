package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    Country country;
    @BeforeEach
    void setUp(){
     country=new Country(1L,"India");
    }

    @Test
    void getId() {
        Long expectedId=1L;
        Long actualId=country.getId();
        assertEquals(actualId,expectedId);
    }

    @Test
    void getCountryName() {
        String expectedCountryName="India";
        String actualCountryName=country.getCountryName();
        assertEquals(actualCountryName,expectedCountryName);
    }

}