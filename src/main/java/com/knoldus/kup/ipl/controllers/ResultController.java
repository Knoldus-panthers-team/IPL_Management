package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    /**
     * @param match_id
     * @param model
     * @return
     */
    @GetMapping("/addScore/{match_id}")
    public String showAddForm(@PathVariable ("match_id") long match_id, Model model) {
        resultService.getScoreForm(model, match_id);
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
        resultService.getEditScoreForm(match_id, model);
        return "update-result";
    }

    @PostMapping("/add")
    public String ScoreSave(Match match, Model model, RedirectAttributes redirectAttributes){
        resultService.getAlertOnSave(match, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    @PostMapping("/update/{id}")
    public String ScoreUpdate(Match match, Model model, RedirectAttributes redirectAttributes){
        resultService.getAlertOnUpdate(redirectAttributes, match);
        return "redirect:/ipl/admin";
    }
}
