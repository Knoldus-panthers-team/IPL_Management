package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Test
    void getAllCountries() {
        countryService.getAllCountries();
        verify(countryRepository).findAll();
    }

    @BeforeEach
    void setUp() {
        this.countryService = new CountryService(this.countryRepository);
    }
}