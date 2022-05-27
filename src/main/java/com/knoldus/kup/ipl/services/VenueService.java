package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Venue;
import com.knoldus.kup.ipl.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VenueService {

    /**
     * Venue Repository  .
     */
    @Autowired
    private VenueRepository venueRepository;

    /**
     * @param venueRepo
     */
    public VenueService(final VenueRepository venueRepo) {
        this.venueRepository = venueRepo;
    }

    /**
     * @param venue
     * @return Venue on saving it to database  .
     */
    public Venue saveVenue(final Venue venue) {
        return venueRepository.save(venue);
    }

    /**
     * @return all Venues  .
     */
    public List<Venue> getAllVenues() {
       return  (List<Venue>) venueRepository.findAll();
    }
}

