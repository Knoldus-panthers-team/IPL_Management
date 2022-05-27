package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    /**
     * Country Repository  .
     */
    @Autowired
    private CountryRepository countryRepository;

    /**
     * @param countryRepo
     */
    public CountryService(final CountryRepository countryRepo) {
        this.countryRepository = countryRepo;
    }

    /**
     * @return list of all Countries  .
     */
    public List<Country> getAllCountries() {
        return (List<Country>) countryRepository.findAll();
    }
}
