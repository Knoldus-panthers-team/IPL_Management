package com.knoldus.kup.ipl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

/**
 * CityEntity.
 */
@Entity
@Table(name = "cities")
public class City {
    /**
     * primary key.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * cityName.
     */
    private String cityName;

    /**
     * one to many mapping for venues.
     */
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Venue> venues = new HashSet<>();

    /**
     * many to one mapping with foreign key countryId.
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


    /**
     * one to many mapping with city entity for teams.
     */
    @OneToMany(mappedBy = "city")
    private Set<Team> teams;

    /**
     *
     * @param id
     * @param cityName
     * @param country
     */
    public City(final Long id, final String cityName, final Country country) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
    }

    /**
     * default constructor.
     */
    public City() { }


    /**
     *
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @param cityName
     */
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    /**
     *
     * @return set of venues
     */
    public Set<Venue> getVenues() {
        return venues;
    }

    /**
     *
     * @param venues
     */
    public void setVenues(final Set<Venue> venues) {
        this.venues = venues;
    }

    /**
     *
     * @return country
     */
    public Country getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(final Country country) {
        this.country = country;
    }

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(final Long id) {
        this.id = id;
    }
}
