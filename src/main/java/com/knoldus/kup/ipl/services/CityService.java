package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    /**
     * City Repository  .
     */
    @Autowired
    CityRepository cityRepository;

    /**
     * @param cityRepo
     */
    public CityService(final CityRepository cityRepo) {
        this.cityRepository = cityRepo;
    }

    /**
     * @return list of all Cities  .
     */
    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }
}
