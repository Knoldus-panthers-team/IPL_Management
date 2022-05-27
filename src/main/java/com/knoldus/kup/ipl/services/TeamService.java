package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    /**
     * Team Repository  .
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * City Service  .
     */
    @Autowired
    private CityService cityService;

    /**
     * @param teamRepo
     */
    public TeamService(final TeamRepository teamRepo) {
        this.teamRepository = teamRepo;
    }

    /**
     * @param team
     * @return Team on saving it to database  .
     */
    public Team saveTeam(final Team team) {
        return teamRepository.save(team);
    }

    /**
     * @return new Team Object  .
     */
    public Team getNewTeamObject() {
        return new Team();
    }

    /**
     * @param id
     * @return Team  .
     */
    public Optional<Team> getTeamById(final Long id) {
        return teamRepository.findById(id);
    }

    /**
     * @param name
     * @return Team by its name  .
     */
    public Optional<Team> getByName(final String name) {
        return this.teamRepository.findByName(name);
    }

    /**
     * @return all Teams  .
     */
    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    /**
     * @param id
     */
    public void deleteTeam(final Long id) {
        teamRepository.deleteById(id);
    }

    /**
     * @param team
     * @param redirectAttributes
     * @return generates alert on save  .
     */
    public RedirectAttributes getAlertOnSave(
            final Team team,
            final RedirectAttributes redirectAttributes) {
        List<Team> list = this.getAllTeams();
        if (list.size() < 15) {
            this.saveTeam(team);
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Team added successfully");
            this.getSuccessMessage(redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Team can not be more than 15");
            redirectAttributes.addFlashAttribute(
                    "messageType",
                    "team");
            redirectAttributes.addFlashAttribute(
                    "alertType",
                    "error");
        }
//        if (result.hasErrors()) {
//            redirectAttributes.addAttribute("message", "Failed");
//            redirectAttributes.addAttribute("alertClass", "alert-danger");
//            redirectAttributes.addFlashAttribute("failed", "failed");
//        }
        return redirectAttributes;
    }

    /**
     * @param model
     * @param teamId
     * @return Team form to edit  .
     */
    public Model getTeamEditForm(
            final Model model,
            final Long teamId) {
        if (this.getTeamById(teamId).isPresent()) {
            System.out.println("..................sfff");
            Team team = this.getTeamById(teamId).orElse(null);
            List<City> cities = cityService.getAllCities();
            model.addAttribute("team", team);
            model.addAttribute("cities", cities);
        }
        return model;
    }

    /**
     * @param team
     * @param redirectAttributes
     * @return generate alert on updating the Team  .
     */
    public RedirectAttributes getAlertOnUpdate(
            final Team team,
            final RedirectAttributes redirectAttributes) {
        teamRepository.save(team);
        redirectAttributes.addFlashAttribute(
                "message",
                "Team updated successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generate success message on saving the Team  .
     */
    public RedirectAttributes getSuccessMessage(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "messageType",
                "team");
        redirectAttributes.addFlashAttribute(
                "alertType",
                "success");
        return redirectAttributes;
    }

    /**
     * @param id
     * @param redirectAttributes
     * @return generate alert on delete  .
     */
    public RedirectAttributes getAlertOnDelete(
            final Long id,
            final RedirectAttributes redirectAttributes) {
        teamRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "message",
                "Team deleted successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generates alert on not finding the team on database  .
     */
    public RedirectAttributes getAlertOnNotFound(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "message",
                "Team not found");
        redirectAttributes.addFlashAttribute(
                "messageType",
                "team");
        redirectAttributes.addFlashAttribute(
                "alertType",
                "error");
        return redirectAttributes;
    }
}
