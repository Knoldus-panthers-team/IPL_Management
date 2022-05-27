package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Venue;
import com.knoldus.kup.ipl.repository.CityRepository;
import com.knoldus.kup.ipl.repository.CountryRepository;
import com.knoldus.kup.ipl.repository.VenueRepository;
import com.knoldus.kup.ipl.securityconfig.User;
import com.knoldus.kup.ipl.securityconfig.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class InitDatabase {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    VenueRepository venueRepository;
    Country country ;
    @Autowired
    UserRepository userRepository;
    @PostConstruct
    public void setCountry() {
        this.setUser();
        country = new Country(1L,"India");
        countryRepository.save(country);
        this.setCities();
        this.setVenues();
    }
    public List<City> setCities() {
        Iterable<City> cityIterable = Arrays.asList(
                new City(1L,"Bangalore", country),
                new City(2L,"Chandigarh", country),
                new City(3L,"Delhi", country),
                new City(4L,"Mumbai", country),
                new City(5L,"Kolkata", country),
                new City(6L,"Chennai", country),
                new City(7L,"Pune", country),
                new City(8L,"Abu Dhabi", country),
                new City(9L,"Punjab", country),
                new City(10L,"Hyderabad", country)
        );
        cityRepository.saveAll(cityIterable);
        return (List<City>) cityIterable;
    }

    public void setVenues() {
        List<City> cities = this.setCities();
        Iterable<Venue> iterable = Arrays.asList(
                new Venue(1L,"M Chinnaswamy Stadium", cities.get(0)),
                new Venue(2L,"Punjab Cricket Association Stadium", cities.get(8)),
                new Venue(3L,"Feroz Shah Kotla", cities.get(2)),
                new Venue(4L,"M Chinnaswamy Stadium", cities.get(4)),
                new Venue(5L,"Eden Gardens", cities.get(4)),
                new Venue(6L,"Rajiv Gandhi International Stadium", cities.get(9)),
                new Venue(7L,"Sheikh Zayed Stadium", cities.get(7))
        );
        venueRepository.saveAll(iterable);
    }
    public void setUser() {
        User user = new User(1L, "admin",
                "$2a$10$IRA.GYhEEZAhjy5A.oh.5OKmRMDcyLXQDzQk7nXsqmWDDQdDHtzEC",
                "ADMIN");
        userRepository.save(user);
    }
}
