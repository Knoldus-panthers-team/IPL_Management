package com.knoldus.kup.ipl.IPL_Management_System.validation;

import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VenueValidation implements ConstraintValidator<VenueValidate, Venue> {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    VenueRepository venueRepository;

    @Override
    public void initialize(VenueValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Venue venue, ConstraintValidatorContext constraintValidatorContext) {
        if (matchRepository == null)
        {
            return false;
        }
        System.out.println("inside if true"+matchRepository.findByVenueId(venue.getId()));

        if (matchRepository.findByVenueId(venue.getId()) != null){
            System.out.println("inside if true");
            return true;
        }

        return false;
    }
}
