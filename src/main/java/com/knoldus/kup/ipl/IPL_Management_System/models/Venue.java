package com.knoldus.kup.ipl.IPL_Management_System.models;

import javax.persistence.*;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    @Column(name = "id", nullable = true)
    private Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
//    @Min(value = 1, message = "please select a city")
    private City city;

    public Venue(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Venue() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
