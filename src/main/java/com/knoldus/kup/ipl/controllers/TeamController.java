package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.services.CityService;
import com.knoldus.kup.ipl.services.PlayerService;
import com.knoldus.kup.ipl.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teams")
public class TeamController {

    /*
    ***************************************** SERVICES ****************************************
     */
    @Autowired
    TeamService teamService;
    @Autowired
    CityService cityService;
    @Autowired
    PlayerService playerService;


    @GetMapping("/addTeam")
    public String addTeamForm(Model model){
        model.addAttribute("team",teamService.getNewTeamObject());
        return "addTeam";
    }

    @PostMapping("/add")
    public String saveTeam(Team team, BindingResult result, RedirectAttributes redirectAttributes){
        teamService.getAlertOnSave(team, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    @GetMapping("/edit/{id}")
    public String UpdateForm(@PathVariable("id") long id,
                                 Model model) {
        teamService.getTeamEditForm(model, id);
        return "update-team";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id") long id, @Valid Team team,
                             BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-team";
        }
        teamService.getAlertOnUpdate(team, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id") long id, Model model,RedirectAttributes redirectAttributes) {
        teamService.getAlertOnDelete(id, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    @GetMapping("/players/{team_id}")
    public String getPlayers(@PathVariable("team_id") Long team_id,Model model){
        if (playerService.getPlayersByTeamId(team_id)!=null && teamService.getTeamById(team_id).isPresent()){
            playerService.getPlayersByTeamIdWithModel(model,team_id);
            return "team-details";
        }
        return "redirect:/ipl";
    }

    @GetMapping("/team/{team_id}")
    public String getTeam(@PathVariable("team_id") Long team_id,Model model, RedirectAttributes redirectAttributes){
        if (playerService.getPlayersByTeamId(team_id)!=null && teamService.getTeamById(team_id).isPresent()){
            playerService.getPlayersByTeamIdWithModel(model,team_id);
            return "admin-teams";
        }
        teamService.getAlertOnNotFound(redirectAttributes);
        return "redirect:/ipl/admin";
    }
}
