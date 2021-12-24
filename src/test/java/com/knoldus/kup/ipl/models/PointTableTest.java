package com.knoldus.kup.ipl.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;


class PointTableTest {

    @Autowired
    PointTable pointTable,pointTable2;
    Team team;

    @BeforeEach
    void setup() {
        team =new Team(1L,"RCB",new City(1L,"Bangalore",new Country()));
        pointTable= new PointTable(1L,4,team,2,2,4,2.2);
        pointTable2= new PointTable(2L,5,team,3,2,6,3.2);
    }

    @Test
    void getTotalMatch() {
        int expectedTotalMatch=4;
        int actualTotalMatch= pointTable.getTotalMatch();
        assertEquals(actualTotalMatch,expectedTotalMatch);
    }

    @Test
    void setTotalMatch() {
        pointTable.setTotalMatch(5);
        int actualTotalMatch = pointTable.getTotalMatch();
        int expectedTotalMatch = 5;
        assertEquals(actualTotalMatch,expectedTotalMatch);
    }

    @Test
    void getWin() {
        int actualWin=pointTable.getWin();
        int expectedWin=2;
        assertEquals(actualWin,expectedWin);
    }

    @Test
    void setWin() {
        pointTable.setWin(3);
        int actualWin=pointTable.getWin();
        int expectedWin= 3;
        assertEquals(actualWin,expectedWin);
    }

    @Test
    void getLose() {
        int actualLose= pointTable.getLose();
        int expectedLose=2;
        assertEquals(actualLose,expectedLose);
    }

    @Test
    void setLose() {
        pointTable.setLose(2);
        int actualLose= pointTable.getLose();
        int expectedLose=2;
        assertEquals(actualLose,expectedLose);
    }

    @Test
    void getPoints() {
        int actualPoints= pointTable.getPoints();
        int expectedPoints=4;
        assertEquals(actualPoints,expectedPoints);
    }

    @Test
    void setPoints() {
        pointTable.setPoints(2);
        int actualLose= pointTable.getLose();
        int expectedLose=2;
        assertEquals(actualLose,expectedLose);
    }

    @Test
    void getNetRunRate() {
        double actualNetRunRate= pointTable.getNetRunRate();
        double expectedNetRunRate=2.2;
        assertEquals(actualNetRunRate,expectedNetRunRate);
    }

    @Test
    void setNetRunRate() {
        pointTable.setNetRunRate(4.4);
        double actualNetRunRate= pointTable.getNetRunRate();
        double expectedNetRunRate=4.4;
        assertEquals(actualNetRunRate,expectedNetRunRate);
    }

    @Test
    void getId() {
        Long actualId= pointTable.getId();
        Long expectedId=1L;
        assertEquals(actualId,expectedId);
    }

    @Test
    void setId() {
        pointTable.setId(4L);
        long actualId=pointTable.getId();
        long expectedId=4L;
        assertEquals(actualId,expectedId);
    }

    @Test
    void getTeam() {
        String expectedTeam="RCB";
        String actualTeamName=pointTable.getTeam().getName();
        assertEquals(actualTeamName,expectedTeam);

    }

    @Test
    void setTeam() {
        pointTable.setTeam(new Team(2L,"MI",new City(2L,"Mumbai",new Country())));
        String actualTeamName=pointTable.getTeam().getName();
        String expectedTeamName="MI";
        assertEquals(actualTeamName,expectedTeamName);
    }

    @Test
    void compareTo() {
        int expectedCompare=2;
        int actualCompare=pointTable.compareTo(this.pointTable2);
        assertEquals(expectedCompare,actualCompare);
    }
}