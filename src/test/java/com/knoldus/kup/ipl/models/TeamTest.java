package com.knoldus.kup.ipl.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Country country;
    City city;
    Team team;
    PointTable pointTable;

    @BeforeEach
    void setUp() {
        country=new Country(1L,"India");
        city=new City(1L,"Mumbai",country);
        team=new Team(1L,"MI",city);
        //Long id, int totalMatch, Team team, int win, int lose, int points, double netRunRate
        pointTable=new PointTable(1L,2,team,2,0,2, 9.611);
        team.setPointTable(pointTable);

        Set<Player> players=new HashSet<>();
        players.add(new Player(1L,"Sachin",team,country,"Batsman"));
        players.add(new Player(2L,"Rohit",team,country,"Batsman"));
        team.setPlayers(players);

        Set<Match> team1Matches=new HashSet<>();
        team1Matches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        team1Matches.add(new Match(2L,"2021-12-18 11:36 PM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        team.setTeam1Matches(team1Matches);
        team.setTeam2Matches(team1Matches);


        List<Match> tossWinmatches=new ArrayList<>();
        tossWinmatches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        tossWinmatches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        team.setTossWinmatches(tossWinmatches);
    }

    @Test
    void getId() {
        long expectedId=1L;
        long actualId= team.getId();
        assertEquals(expectedId,actualId);
    }

    @Test
    void setId() {
        long expectedId=1L;
        team.setId(1L);
        long actualId= team.getId();
        assertEquals(expectedId,actualId);
    }

    @Test
    void getName() {
        String expectedName="MI";
        String actualName= team.getName();
        assertEquals(expectedName,actualName);
    }

    @Test
    void setName() {
        team.setName("MI");
        String expectedName="MI";
        String actualName=team.getName();
        assertEquals(expectedName,actualName);
    }

    @Test
    void getCity() {
        long expectedId=1L;
        long actualId=city.getId();
        assertEquals(expectedId,actualId);

        String expectedCityName="Mumbai";
        String actualCityName=city.getCityName();
        assertEquals(expectedCityName,actualCityName);

        Country expectedCountry=new Country(1L,"India");
        Long actualCountryId=country.getId();
        String actualCountryName=country.getCountryName();
        assertSame(expectedCountry.getId(),actualCountryId);
        assertEquals(expectedCountry.getCountryName(),actualCountryName);
    }

    @Test
    void setCity() {
        City expectedCity=new City(2L,"Delhi",new Country(1L,"India"));
        team.setCity(expectedCity);
        City actualCity=team.getCity();
        assertTrue(expectedCity.getId()==actualCity.getId() && expectedCity.getCityName().equals(actualCity.getCityName()) && expectedCity.getCountry().getId()==actualCity.getCountry().getId() && expectedCity.getCountry().getCountryName()==actualCity.getCountry().getCountryName());
    }

    @Test
    void getPlayers() {
        Set<Player> getPlayers=team.getPlayers();
        int size=getPlayers.size();
        assertTrue(size>1);
    }

    @Test
    void setPlayers() {
        Set<Player> players=new HashSet<>();
        players.add(new Player(1L,"Sachin",team,country,"Batsman"));
        players.add(new Player(2L,"Rohit",team,country,"Batsman"));
        team.setPlayers(players);
        int size=players.size();
        assertTrue(size>1);
    }

    @Test
    void getTeam1Matches() {
        Set<Match> getTeam1Matches=team.getTeam1Matches();
        int size= getTeam1Matches.size();
        assertTrue(size>1);
    }

    @Test
    void setTeam1Matches() {
        Set<Match> setTeam1Matches=new HashSet<>();
        setTeam1Matches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        setTeam1Matches.add(new Match(2L,"2021-12-18 11:36 PM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        int size=setTeam1Matches.size();
        assertTrue(size>1);
    }

    @Test
    void getTeam2Matches() {
        Set<Match> getTeam2Matches=team.getTeam2Matches();
        int size=getTeam2Matches.   size();
        assertTrue(size>1);
    }

    @Test
    void setTeam2Matches() {
        Set<Match> setTeam2Matches=new HashSet<>();
        setTeam2Matches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        setTeam2Matches.add(new Match(2L,"2021-12-18 11:36 PM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        int size=setTeam2Matches.size();
        assertTrue(size>1);
    }

    @Test
    void getPointTable() {
        //Long id, int totalMatch, Team team, int win, int lose, int points, double netRunRate
        //pointTable=new PointTable(1L,2,team,2,0,2, 9.611);
        PointTable actualPointTable=team.getPointTable();
        long expectedId=1L;
        long actualId= actualPointTable.getId();

        int expectedTotalMatch=2;
        int actualTotalMatch= actualPointTable.getTotalMatch();

        Team expectedTeam=team;
        Team actualTeam=actualPointTable.getTeam();

        int expectedWin=2;
        int actualWin=actualPointTable.getWin();

        int expectedLose=0;
        int actualLose=actualPointTable.getLose();

        int expectedPoints=2;
        int actualPoints=actualPointTable.getPoints();

        double expectedNetRunRate=9.611;
        double actualNetRunRate=actualPointTable.getNetRunRate();

        assertTrue(expectedId==actualId && expectedTotalMatch==actualTotalMatch && expectedTeam==actualTeam && expectedWin==actualWin && expectedLose==actualLose && expectedPoints==actualPoints && expectedNetRunRate==actualNetRunRate);
    }

    @Test
    void setPointTable() {
        PointTable setPointTable=new PointTable(1L,2,team,2,0,2, 9.611);
        PointTable expectedPointTable=pointTable;
        PointTable actualPointTable=setPointTable;
        assertTrue(expectedPointTable.getId()==actualPointTable.getId() && expectedPointTable.getTotalMatch()==actualPointTable.getTotalMatch() &&
                    expectedPointTable.getTeam().getId()==actualPointTable.getTeam().getId() && expectedPointTable.getTeam().getName()==actualPointTable.getTeam().getName() &&
                    expectedPointTable.getTeam().getCity()==actualPointTable.getTeam().getCity() && expectedPointTable.getWin() ==actualPointTable.getWin() && actualPointTable.getLose() ==expectedPointTable.getLose() &&
                    expectedPointTable.getPoints()==actualPointTable.getPoints() && expectedPointTable.getNetRunRate() ==actualPointTable.getNetRunRate());
    }

    @Test
    void getTossWinmatches() {
        List<Match> getTossWinMatches=team.getTossWinmatches();
        int size=getTossWinMatches.size();
        assertTrue(size>1);
    }

    @Test
    void setTossWinmatches() {
        List<Match> setTossWinmatches=new ArrayList<>();
        setTossWinmatches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        setTossWinmatches.add(new Match(1L,"2021-12-17 11:36 AM",new Venue(1L,"Feroz Shah Kotla",city),team,team));
        team.setTossWinmatches(setTossWinmatches);

        int size= setTossWinmatches.size();
        assertTrue(size>1);
    }
}