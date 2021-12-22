package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "players")
@Service
public class Player {
    @Id
    @Column(name = "id", nullable = false, insertable = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(min=1, message="Player name is required")
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "country_id")
    private Country country;
    private String role;

    public Player(Long id, String name, Team team, Country country, String role) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.country = country;
        this.role = role;
    }

    public Player(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
