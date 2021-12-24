package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.*;
import com.knoldus.kup.ipl.IPL_Management_System.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collections;
import java.util.List;

@RequestMapping("ipl")
@Controller
public class IPLController {

//    ---------------------- Player Part---------------------------------
    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @Autowired
    MatchService matchService;

    @Autowired
    CityService cityService;

    @Autowired
    VenueService venueService;

    @Autowired
    CountryService countryService;

    @Autowired
    PointService pointService;


    @GetMapping("admin")
    public String getAdminDashboard(Model model){
        Player player = playerService.getNewPlayerObject();
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players",players);
        model.addAttribute("player",player);

        Team team = teamService.getNewTeamObject();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
        model.addAttribute("team",team);

        List<City> citiesList = cityService.getAllCities();
        model.addAttribute("cities",citiesList);

        List<Venue> venues= venueService.getAllVenues();
        model.addAttribute("venues",venues);

        List<Country> countries= countryService.getAllCountries();
        model.addAttribute("countries",countries);

//        Point table list
        List<PointTable> pointTables= pointService.getAllTables();
        Collections.sort(pointTables);
        model.addAttribute("pointTables",pointTables);

        Match match = new Match();
        match.setMatchWinner("NA");
        match.setResult("NA");
        match.setResult("NA");
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);
        model.addAttribute("match",match);
//        model.addAttribute("message",message);
        return "index";
    }

    @GetMapping("")
    public String getDashboard(Model model){
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players",players);

        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);

        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);

        //        Point table list
        List<PointTable> pointTables= pointService.getAllTables();
        Collections.sort(pointTables);
        model.addAttribute("pointTables",pointTables);
        return "dashboard";
    }
}
