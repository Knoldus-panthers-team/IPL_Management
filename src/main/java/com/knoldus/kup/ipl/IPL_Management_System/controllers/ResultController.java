package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.*;
import com.knoldus.kup.ipl.IPL_Management_System.repository.*;
import com.knoldus.kup.ipl.IPL_Management_System.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("result")
public class ResultController {

    @Autowired
    MatchService matchService;

    @Autowired
    VenueService venueService;

    @Autowired
    TeamService teamService;

    @Autowired
    ResultService resultService;

    @Autowired
    UpdateResultService updateResultService;

    @Autowired
    PointService pointService;

    @GetMapping("/addScore/{match_id}")
    public String showAddForm(@PathVariable ("match_id") long match_id, Model model) {

        Match match = matchService.getMatchById(match_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + match_id));
        System.out.println("result add method"+match.getTeam1().getName()+match.getTeam1Over().equals("Yet to be played"));

        if (match.getTeam1Over().equals("Yet to be played")){
            match.setTeam1Over("");
            match.setTeam2Over("");
        }

        model.addAttribute("match",match);

        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);

        List<Venue> venues= venueService.getAllVenues();
        model.addAttribute("venues",venues);

        return "add-result";
    }

    @GetMapping("/editScore/{match_id}")
    public String showUpdateForm(@PathVariable ("match_id") long match_id, Model model) {

        Match match = matchService.getMatchById(match_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + match_id));
        System.out.println("result edit method"+match.getTeam1().getName());

        model.addAttribute("match",match);

        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);

        List<Venue> venues= venueService.getAllVenues();
        model.addAttribute("venues",venues);
        model.addAttribute("team1", match.getTeam1().getName());
        model.addAttribute("team2", match.getTeam2().getName());

        return "update-result";
    }

    @PostMapping("/add/{id}")
    public String ScoreSave(@PathVariable("id") long id, Match match, Model model, RedirectAttributes redirectAttributes){
        System.out.println("add result method---------------------------------"+match.getTeam1Over());
        resultService.getResult(match);
        pointService.addPointTable(match);
        redirectAttributes.addFlashAttribute("message", "Score added successfully");
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

    @PostMapping("/update/{id}")
    public String ScoreUpdate(@PathVariable("id") long id, Match match, Model model, RedirectAttributes redirectAttributes){
        System.out.println("result update method---------------------------------"+match.getTeam1Over());
        updateResultService.updatePointTable(match);
        redirectAttributes.addFlashAttribute("message", "Score updated successfully");
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

}
