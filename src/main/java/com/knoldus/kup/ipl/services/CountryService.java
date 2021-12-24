package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
        return (List<Country>) countryRepository.findAll();
    }
}
