package com.knoldus.kup.ipl.IPL_Management_System.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VenueValidation.class)
public @interface VenueValidate {
    String message () default "This slot is booked for other match";

    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};


}
