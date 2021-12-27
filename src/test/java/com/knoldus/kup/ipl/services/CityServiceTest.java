package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    City city1,city2,city3;
    List<City> citiesList;

    @BeforeEach
    void setUp() {
        this.cityService = new CityService(this.cityRepository);
        Country country = new Country(1L,"India");
        city1 = new City(1L,"Channai",country);
        city2 = new City(1L,"Kolkata",country);
        city3 = new City(1L,"Agra",country);
        citiesList = Arrays.asList(city1,city2,city3);
    }

    @Test
    void getAllCities() {
        when(cityRepository.findAll()).thenReturn(citiesList);
        assertThat(cityService.getAllCities()).isEqualTo(citiesList);
    }
}