package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.PointRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import com.knoldus.kup.ipl.IPL_Management_System.services.MatchService;
import com.knoldus.kup.ipl.IPL_Management_System.services.PointService;
import com.knoldus.kup.ipl.IPL_Management_System.services.ResultService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchService matchService;

    @RequestMapping("/addMatch")
    public String getAddForm(Model model){
        return matchService.addForm(model);
    }

    @PostMapping("/save")
    public String saveMatch(@Valid Match match, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "add-match";
        }
        matchService.saveMatch(match);
        redirectAttributes.addFlashAttribute("message", "Match scheduled successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
//        return matchService.saveMatch(match);
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        return matchService.editMatchForm(id, model);
    }

    @PostMapping("/update/{id}")
    public String matchUpdate(@PathVariable long id, Match match, Model model,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        if (matchService.isDatePresent(match.getMatchDate(),bindingResult) &&
//                matchService.isTeam1Exist(match.getTeam1(), bindingResult))
//        {
//            bindingResult.addError(new FieldError("match","team1","Match already scheduled for this team at this date"));
//        }
//        else if(matchService.isDatePresent(match.getMatchDate(),bindingResult) &&
//                matchService.isTeam2Exist(match.getTeam2(), bindingResult))
//        {
//            bindingResult.addError(new FieldError("match","team2","Match already scheduled for this team"));
//        }
//        else if (matchService.isVenuePresent(match.getVenue().getId(),bindingResult) &&
//                matchService.isDatePresent(match.getMatchDate(), bindingResult))
//        {
//            bindingResult.addError(new FieldError("match","matchDate","This slot is booked for other match. Please select another date or venue"));
//        }
        if (bindingResult.hasErrors()){
            return "update-match";
        }
        matchRepository.save(match);
        redirectAttributes.addFlashAttribute("message", "Match rescheduled successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/ipl/admin";
    }

    @RequestMapping("/list")
    public String getMatches(Model model){

        List<Match> matches = (List<Match>) matchRepository.findAll();
                Match match = new Match();
        model.addAttribute("match",match);
        model.addAttribute("matchList",matches);
//        System.out.println(matches.stream().findFirst().get().getTeam().getPlayers().stream().findFirst().get().getName());
        return "match-details";
    }

    @RequestMapping("/test")
    public String list(Model model){
        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("matches", matches);
        return "match-dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteMatch(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + id));
        matchRepository.delete(match);
        redirectAttributes.addFlashAttribute("message", "Match deleted successfully");
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }

}
