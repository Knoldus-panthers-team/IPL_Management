package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.*;
import com.knoldus.kup.ipl.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collections;
import java.util.List;

/**
 * Class IPLController.
 */
@RequestMapping("ipl")
@Controller
public class IPLController {
    /**
     * Injecting player service.
     */
    @Autowired
    private PlayerService playerService;
    /**
     * Injecting team service.
     */
    @Autowired
    private TeamService teamService;
    /**
     * Injecting match service.
     */
    @Autowired
    private MatchService matchService;
    /**
     * Injecting city service.
     */
    @Autowired
    private CityService cityService;
    /**
     * Injecting venue service.
     */
    @Autowired
    private VenueService venueService;
    /**
     * Injecting country service.
     */
    @Autowired
    private CountryService countryService;
    /**
     * Injecting point service.
     */
    @Autowired
    private PointService pointService;


    /**
     * @param model
     * @return index
     */
    @GetMapping("admin")
    public String getAdminDashboard(final Model model) {
        Player player = playerService.getNewPlayerObject();
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        model.addAttribute("player", player);

        Team team = teamService.getNewTeamObject();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("team", team);

        List<City> citiesList = cityService.getAllCities();
        model.addAttribute("cities", citiesList);

        List<Venue> venues = venueService.getAllVenues();
        model.addAttribute("venues", venues);

        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

//        Point table list
        List<PointTable> pointTables = pointService.getAllTables();
        Collections.sort(pointTables);
        model.addAttribute("pointTables", pointTables);

        Match match = new Match();
        match.setMatchWinner("NA");
        match.setResult("NA");
        match.setResult("NA");
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);
        model.addAttribute("match", match);
//        model.addAttribute("message",message);
        return "index";
    }

    /**
     * @param model
     * @return dashboard.
     */
    @GetMapping("/")
    public String getDashboard(final Model model) {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);

        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);

        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);

        //        Point table list
        List<PointTable> pointTables = pointService.getAllTables();
        Collections.sort(pointTables);
        model.addAttribute("pointTables", pointTables);
        return "dashboard";
    }
}
