package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.City;
import com.knoldus.kup.ipl.IPL_Management_System.repository.CityRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.PlayerRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.services.CityService;
import com.knoldus.kup.ipl.IPL_Management_System.services.TeamService;
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

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CityRepository cityRepository;

    /*
    ***************************************** SERVICES ****************************************
     */
    @Autowired
    TeamService teamService;
    @Autowired
    CityService cityService;


    @GetMapping("/addTeam")
    public String addTeamForm(Model model){
        Team team = new Team();
        model.addAttribute("team",team);
        return "addTeam";
    }

    @GetMapping("/suggest-event")
    public String suggestEvent(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("message",message);
        return "suggestEvent";
    }

//    @PostMapping("/suggest-event")
//    public String receiveSuggestedEvent( RedirectAttributes redirectAttributes) {
//        redirectAttributes.addAttribute("message", "Success");
//        return "redirect:/suggest-event";
//    }

    @PostMapping("/add")
    public String addTeam(Team team,BindingResult result,RedirectAttributes redirectAttributes){
        List<Team> list= teamService.getAllTeams();
        if(list.size()<15) {
            teamService.saveTeam(team);
            redirectAttributes.addFlashAttribute("message", "Team added successfully");
            redirectAttributes.addFlashAttribute("messageType", "team");
            redirectAttributes.addFlashAttribute("alertType", "success");
        }
        else {
            System.out.println("Total teams cannot exceed 15");
            redirectAttributes.addFlashAttribute("message", "Team can not be more than 15");
            redirectAttributes.addFlashAttribute("messageType", "team");
            redirectAttributes.addFlashAttribute("alertType", "error");
        }
        if (result.hasErrors()) {
            redirectAttributes.addAttribute("message", "Failed");
            redirectAttributes.addAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("failed", "failed");
            return "redirect:/ipl/admin";
        }
        return "redirect:/ipl/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,
                                 Model model) {
        Team team = teamService.getTeam(id);
        List<City> cities = cityService.getAllCities();
        model.addAttribute("team", team);
        model.addAttribute("cities", cities);
        return "update-team";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id") long id, @Valid Team team,
                             BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-team";
        }
        teamService.saveTeam(team);
//        teamRepository.save(team);
        redirectAttributes.addFlashAttribute("message", "Team updated successfully");
        redirectAttributes.addFlashAttribute("messageType", "team");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/ipl/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id") long id, Model model,RedirectAttributes redirectAttributes) {
//        Team team = teamService.getTeam(id);
//        teamRepository.delete(team);

        redirectAttributes.addFlashAttribute("message", "Team deleted successfully");
        redirectAttributes.addFlashAttribute("messageType", "team");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/ipl/admin";
    }

    @GetMapping("/players/{team_id}")
    public String getPlayers(@PathVariable("team_id") Long team_id,Model model){
        Set<Player> players = playerRepository.findByTeamId(Long.valueOf(team_id));
        model.addAttribute("players",players);
        System.out.println("--------------------------"+teamRepository.findById(team_id).get().getName() );
        model.addAttribute("team", teamRepository.findById(team_id).get());

        return "team-details";
    }

    @GetMapping("/team/{team_id}")
    public String getTeam(@PathVariable("team_id") Long team_id,Model model){
        Set<Player> players = playerRepository.findByTeamId(Long.valueOf(team_id));
        model.addAttribute("players",players);
        model.addAttribute("team", teamRepository.findById(team_id).get());
        return "admin-teams";
    }


}
