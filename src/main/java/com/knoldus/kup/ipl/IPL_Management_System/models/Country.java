package com.knoldus.kup.ipl.IPL_Management_System.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    String countryName;

    @OneToMany(mappedBy = "country",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<State> states;

    @OneToMany(mappedBy = "country",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Player> players;

    public Country(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Country(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String setCountryName(String countryName) {
        this.countryName = countryName;
        return countryName;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
