package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.*;
import com.knoldus.kup.ipl.IPL_Management_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("ipl")
@Controller
public class IPLController {

//    ---------------------- Player Part---------------------------------
    @Autowired
    PlayerRepository playerDao;

    @Autowired
    TeamRepository teamDao;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    PointRepository pointRepository;


    @GetMapping("admin")
    public String getAdminDashboard(Model model){
        Player player = new Player();
        List<Player> players = (List<Player>) playerDao.findAll();
        model.addAttribute("players",players);
        model.addAttribute("player",player);

        Team team =new Team();
        List<Team> teams = (List<Team>) teamDao.findAll();
        model.addAttribute("teams",teams);
        model.addAttribute("team",team);

        List<City> citiesList= (List<City>) cityRepository.findAll();
        model.addAttribute("cities",citiesList);

        List<Venue> venues= (List<Venue>) venueRepository.findAll();
        model.addAttribute("venues",venues);

        List<Country> countries= (List<Country>) countryRepository.findAll();
        model.addAttribute("countries",countries);

//        Point table list
        List<PointTable> pointTables= (List<PointTable>) pointRepository.findAll();
        Collections.sort(pointTables);
        model.addAttribute("pointTables",pointTables);

        Match match = new Match();
        match.setMatchWinner("NA");
        match.setResult("NA");
        match.setResult("NA");
        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("matches", matches);
        model.addAttribute("match",match);
//        model.addAttribute("message",message);
        return "index";
    }

    @GetMapping("")
    public String getDashboard(Model model){
        List<Player> players = (List<Player>) playerDao.findAll();
        model.addAttribute("players",players);

        List<Team> teams = (List<Team>) teamDao.findAll();
        model.addAttribute("teams",teams);

        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("matches", matches);

        //        Point table list
        List<PointTable> pointTables= (List<PointTable>) pointRepository.findAll();
        Collections.sort(pointTables);
        model.addAttribute("pointTables",pointTables);

        LocalDate startDate = java.time.LocalDate.now(); //LocalDate.of(2021, 1, 1);
//        System.out.println(startDate);

        return "dashboard";
    }


    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}
