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
    ResultService resultService;
    
//    @Autowired
//    private ProducerService kafkaService;
    
    @Autowired
    UpdateResultService updateResultService;
    
    @Autowired
    PointService pointService;
    
    
    @GetMapping("/addScore/{match_id}")
    public String showAddForm(@PathVariable ("match_id") long match_id, Model model) {
        resultService.addScores(match_id, model);
        return "add-result";
    }
    
    @GetMapping("/editScore/{match_id}")
    public String showUpdateForm(@PathVariable ("match_id") long match_id, Model model) {
        resultService.editScores(match_id, model);
        return "update-result";
    }
    
    @PostMapping("/add/{id}")
    public String ScoreSave(@PathVariable("id") long id, Match match, Model model, RedirectAttributes redirectAttributes){
        resultService.getResult(match);
        pointService.addPointTable(match);
//        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute("message", "Score added successfully");
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/ipl/admin";
    }
    
    @PostMapping("/update/{id}")
    public String ScoreUpdate(@PathVariable("id") long id, Match match, Model model, RedirectAttributes redirectAttributes){
//        kafkaService.sendMatch(match);
        updateResultService.updatePointTable(match, redirectAttributes);
        return "redirect:/ipl/admin";
    }
    
}