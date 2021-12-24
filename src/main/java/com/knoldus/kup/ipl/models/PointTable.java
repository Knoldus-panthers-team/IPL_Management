package com.knoldus.kup.ipl.models;

import javax.persistence.*;

@Entity
public class PointTable implements Comparable<PointTable>{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int totalMatch;

    @OneToOne
    @JoinColumn( name = "team_id")
    Team team;

    int win;
    int lose;
    int points;

    public PointTable(Long id, int totalMatch, Team team, int win, int lose, int points, double netRunRate) {
        this.id = id;
        this.totalMatch = totalMatch;
        this.team = team;
        this.win = win;
        this.lose = lose;
        this.points = points;
        this.netRunRate = netRunRate;
    }

    double netRunRate;

    public PointTable() {

    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public int setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
        return totalMatch;
    }

    public int getWin() {
        return win;
    }

    public int setWin(int win) {
        this.win = win;
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int setLose(int lose) {
        this.lose = lose;
        return lose;
    }

    public int getPoints() {
        return points;
    }

    public double setPoints(int points) {
        this.points = points;
        return 0;
    }

    public double getNetRunRate() {
        return netRunRate;
    }

    public void setNetRunRate(double netRunRate) {
        this.netRunRate = netRunRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int compareTo(PointTable pointTable) {
        return pointTable.points-this.points;
    }
}
