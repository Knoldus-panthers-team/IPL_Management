package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class VenueServiceTest {

    @Mock
    VenueRepository venueRepository;

    VenueService venueService;

    @Test
    void getAllVenues_CorrectIfFindAllVenues() {
        venueService.getAllVenues();
        verify(venueRepository).findAll();
    }

    @BeforeEach
    void setUp() {
        this.venueService = new VenueService(this.venueRepository);
    }
}