package com.knoldus.kup.ipl.IPL_Management_System.models;

import javax.management.StandardEmitterMBean;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "states")
public class State {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String stateName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id")
    private Country country;


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
