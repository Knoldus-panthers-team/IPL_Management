package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities(){
        return (List<City>) cityRepository.findAll();
    }
}
