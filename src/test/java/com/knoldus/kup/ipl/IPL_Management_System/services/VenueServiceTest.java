package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Country;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class VenueServiceTest {

    @MockBean
    VenueRepository venueRepository;

    @Autowired
    VenueService venueService;

    private Venue venue1,venue2;

    @BeforeEach
    void setUp() {
        System.out.println("Started");
        City city1 = new City(1L, "Kolkata", new Country());
        City city2 = new City(2L, "Chennai", new Country());
        venue1 = new Venue(1L,"Kolkata Stadium",city1);
        venue2 = new Venue(1L,"Chidambaram Stadium",city2);
    }

    @Test
    void getAllVenues_CorrectIfFindAllVenues() {
        venueService.getAllVenues();
        verify(venueRepository).findAll();
    }

    @Test
    void saveVenue_ReturnTrueIfVenueSaved(){
        when(venueRepository.save(venue1)).thenReturn(venue1);
        assertNotNull(venueService.saveVenue(venue1));
    }
}