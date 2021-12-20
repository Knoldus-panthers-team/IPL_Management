package com.knoldus.kup.ipl.IPL_Management_System.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String cityName;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Venue> venues=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    @OneToMany(mappedBy = "city")
    private Set<Team> teams;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Set<Venue> getVenues() {
        return venues;
    }

    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
