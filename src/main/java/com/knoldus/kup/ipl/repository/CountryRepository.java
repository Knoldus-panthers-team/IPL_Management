package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
