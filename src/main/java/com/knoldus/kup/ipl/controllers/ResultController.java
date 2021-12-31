package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.models.Venue;
import com.knoldus.kup.ipl.services.*;
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
    private MatchService matchService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private UpdateResultService updateResultService;

    @Autowired
    private PointService pointService;

    @Autowired
    private ProducerService kafkaService;

    /**
     * @param match_id
     * @param model
     * @return
     */
    @GetMapping("/addScore/{match_id}")
    public String showAddForm(@PathVariable ("match_id") long match_id, Model model) {

        Match match = matchService.getMatchById(match_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + match_id));
        System.out.println("result add method" + match.getTeam1().getName()+match.getTeam1Over().equals("Yet to be played"));

        if (match.getTeam1Over().equals("Yet to be played")) {
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

    /**
     *
     * @param match_id
     * @param model
     * @return
     */
    @GetMapping("/editScore/{match_id}")
    public String showUpdateForm(@PathVariable ("match_id") long match_id, Model model) {

        Match match = matchService.getMatchById(match_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + match_id));

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
        resultService.getResult(match);
        pointService.addPointTable(match);
        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute("message", "Score added successfully");
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

    @PostMapping("/update/{id}")
    public String ScoreUpdate(@PathVariable("id") long id, Match match, Model model, RedirectAttributes redirectAttributes){
        updateResultService.updatePointTable(match);
        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute("message", "Score updated successfully");
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

}
