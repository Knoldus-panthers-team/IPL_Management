package com.knoldus.kup.ipl.IPL_Management_System.models;

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
    double netRunRate;

    public int getTotalMatch() {
        return totalMatch;
    }

    public void setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
