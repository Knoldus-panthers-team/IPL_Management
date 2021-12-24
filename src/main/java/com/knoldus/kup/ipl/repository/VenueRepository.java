package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue,Long> {
}
