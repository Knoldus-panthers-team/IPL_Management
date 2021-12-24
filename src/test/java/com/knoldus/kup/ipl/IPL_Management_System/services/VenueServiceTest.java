package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class VenueServiceTest {

    @Autowired
    CityRepository cityRepository;

    @MockBean
    VenueRepository venueRepository;

    @Autowired
    VenueService venueService;

    private Venue venue;

    @BeforeEach
    void setUp() {
//        this.venueService = new VenueService(this.venueRepository);
        System.out.println("Started");
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
        cityRepository.save(city1);
        cityRepository.save(city2);
        venue = new Venue(1L,"Kolkata Stadium",city1);
    }

    @Test
    void getAllVenues_CorrectIfFindAllVenues() {
        venueService.getAllVenues();
        verify(venueRepository).findAll();
    }

    @Test
    void saveVenue_ReturnTrueIfVenueSaved(){
        venueService.saveVenue(venue);
        assertNotNull(venueRepository.findById(venue.getId()));
    }
}