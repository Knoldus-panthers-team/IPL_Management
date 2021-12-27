package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.models.Venue;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.services.MatchService;
import com.knoldus.kup.ipl.services.TeamService;
import com.knoldus.kup.ipl.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    VenueService venueService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @RequestMapping("/addMatch")
    public String getAddForm(Model model){
        List<Venue> venues= venueService.getAllVenues();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
        model.addAttribute("venues",venues);
        model.addAttribute("match",matchService.getNewMatch());
        return "add-match";
    }

    @PostMapping("/save")
    public String saveMatch(final @Valid Match match, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "add-match";
        }
        matchService.saveMatch(match);
        redirectAttributes.addFlashAttribute("message", "Match scheduled successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Match match = matchService.getMatchById(id).orElseThrow(()->new IllegalArgumentException("Invalid match id: "+id));
        List<Venue> venues = (List<Venue>) venueService.getAllVenues();
        List<Team> teams = (List<Team>) teamService.getAllTeams();
        model.addAttribute("venues",venues);
        model.addAttribute("teams",teams);
        model.addAttribute("match", match);
        return "update-match";
    }

    @PostMapping("/update/{id}")
    public String matchUpdate(@PathVariable long id, Match match, Model model,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(matchService.isSlotBooked(match)){
            redirectAttributes.addFlashAttribute("message", "This slot is booked for other match. Please select another date or venue");
            redirectAttributes.addFlashAttribute("messageType", "match");
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:/matches/edit/"+match.getId();
        }
        if(matchService.isTeamSame(match)){
            redirectAttributes.addFlashAttribute("message", "Teams can't be same");
            redirectAttributes.addFlashAttribute("messageType", "match");
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:/matches/edit/"+match.getId();
        }
        matchRepository.save(match);
        redirectAttributes.addFlashAttribute("message", "Match rescheduled successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

    @RequestMapping("/list")
    public String getMatches(Model model){
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("match",matchService.getNewMatch());
        model.addAttribute("matchList",matches);
        return "match-details";
    }

    @RequestMapping("/test")
    public String list(Model model){
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);
        return "match-dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteMatch(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        matchService.deleteMatch(id);
        redirectAttributes.addFlashAttribute("message", "Match deleted successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

}
