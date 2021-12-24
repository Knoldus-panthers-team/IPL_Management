package com.knoldus.kup.ipl.IPL_Management_System.models;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
@Service
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min=3,message = "Team name should contain atleast 3 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Player> players;

    @OneToMany(mappedBy = "team1",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Match> team1Matches;

    @OneToMany(mappedBy = "team2",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Match> team2Matches;

    @OneToOne(mappedBy = "team",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private PointTable pointTable;

    @OneToMany(mappedBy = "tossWinnerTeam",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Match> tossWinmatches;

    public Team(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Team(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Match> getTeam1Matches() {
        return team1Matches;
    }

    public void setTeam1Matches(Set<Match> team1Matches) {
        this.team1Matches = team1Matches;
    }

    public Set<Match> getTeam2Matches() {
        return team2Matches;
    }

    public void setTeam2Matches(Set<Match> team2Matches) {
        this.team2Matches = team2Matches;
    }

    public PointTable getPointTable() {
        return pointTable;
    }

    public void setPointTable(PointTable pointTable) {
        this.pointTable = pointTable;
    }

    public List<Match> getTossWinmatches() {
        return tossWinmatches;
    }

    public void setTossWinmatches(List<Match> tossWinmatches) {
        this.tossWinmatches = tossWinmatches;
    }


}
