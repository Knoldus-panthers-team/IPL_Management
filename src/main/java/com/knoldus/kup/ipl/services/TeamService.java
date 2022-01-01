package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;
    
    @Autowired
    CityService cityService;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team saveTeam(Team team){
        return teamRepository.save(team);
    }

    public Team getNewTeamObject(){
        return new Team();
    }

    public Optional<Team> getTeamById(Long id){
        return teamRepository.findById(id);
    }

    public Optional<Team> getByName(String name){
        return this.teamRepository.findByName(name);
    }

    public List<Team> getAllTeams(){
        return (List<Team>) teamRepository.findAll();
    }

    public void deleteTeam(Long id){
        teamRepository.deleteById(id);
    }
    
    public RedirectAttributes getAlertOnSave(Team team, RedirectAttributes redirectAttributes){
        List<Team> list= this.getAllTeams();
        if(list.size()<15) {
            this.saveTeam(team);
            redirectAttributes.addFlashAttribute("message", "Team added successfully");
            this.getSuccessMessage(redirectAttributes);
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Team can not be more than 15");
            redirectAttributes.addFlashAttribute("messageType", "team");
            redirectAttributes.addFlashAttribute("alertType", "error");
        }
//        if (result.hasErrors()) {
//            redirectAttributes.addAttribute("message", "Failed");
//            redirectAttributes.addAttribute("alertClass", "alert-danger");
//            redirectAttributes.addFlashAttribute("failed", "failed");
//        }
        return redirectAttributes;
    }
    
    public Model getTeamEditForm(Model model, Long teamId){
        if(this.getTeamById(teamId).isPresent()) {
            Team team = this.getTeamById(teamId).orElse(null);
            List<City> cities = cityService.getAllCities();
            model.addAttribute("team", team);
            model.addAttribute("cities", cities);
        }
        return model;
    }
    
    public RedirectAttributes getAlertOnUpdate(Team team, RedirectAttributes redirectAttributes){
        teamRepository.save(team);
        redirectAttributes.addFlashAttribute("message", "Team updated successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getSuccessMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("messageType", "team");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertOnDelete(Long id,RedirectAttributes redirectAttributes){
        teamRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Team deleted successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertOnNotFound(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Team not found");
        redirectAttributes.addFlashAttribute("messageType", "team");
        redirectAttributes.addFlashAttribute("alertType", "error");
        return redirectAttributes;
    }
    
}
