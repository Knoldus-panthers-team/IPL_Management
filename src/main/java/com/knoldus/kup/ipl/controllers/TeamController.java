package com.knoldus.kup.ipl.controllers;

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

/**
 * Class TeamController.
 */
@Controller
@RequestMapping("/teams")
public class TeamController {
    /**
     * Injecting team Service.
     */
    @Autowired
    private TeamService teamService;
    /**
     * Injecting city Service.
     */
    @Autowired
    private CityService cityService;
    /**
     * Injecting Player Service.
     */
    @Autowired
    private PlayerService playerService;

    /**
     *
     * @param team
     * @param result
     * @param redirectAttributes
     * @return admin dashboard
     */
    @PostMapping("/add")
    public String saveTeam(final Team team,
                           final BindingResult result,
                           final RedirectAttributes redirectAttributes) {
        teamService.getAlertOnSave(team, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param id
     * @param model
     * @return update-team
     */
    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") final long id,
                                 final Model model) {
        teamService.getTeamEditForm(model, id);
        return "update-team";
    }

    /**
     *
     * @param id
     * @param team
     * @param bindingResult
     * @param redirectAttributes
     * @return admin dashboard
     */
    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id")
                                 final long id, @Valid final Team team,
                             final BindingResult bindingResult,
                             final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-team";
        }
        teamService.getAlertOnUpdate(team, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param id
     * @param model
     * @param redirectAttributes
     * @return admin dashboard
     */
    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id")
                                 final long id, final Model model,
                             final RedirectAttributes redirectAttributes) {
        teamService.getAlertOnDelete(id, redirectAttributes);
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param teamId
     * @param model
     * @return ipl homepage
     */
    @GetMapping("/players/{teamId}")
    public String getPlayers(@PathVariable("teamId")
                                 final Long teamId, final Model model) {
        if (playerService.getPlayersByTeamId(teamId) != null
                && teamService.getTeamById(teamId).isPresent()) {
            playerService.getPlayersByTeamIdWithModel(model, teamId);
            return "team-details";
        }
        return "redirect:/ipl";
    }

    /**
     *
     * @param teamId
     * @param model
     * @param redirectAttributes
     * @return admin dashboard
     */
    @GetMapping("/team/{teamId}")
    public String getTeamPlayers(@PathVariable("teamId")
                                     final Long teamId, final Model model,
                                 final RedirectAttributes redirectAttributes) {
        if (playerService.getPlayersByTeamId(teamId) != null
                && teamService.getTeamById(teamId).isPresent()) {
            playerService.getPlayersByTeamIdWithModel(model, teamId);
            return "admin-teams";
        }
        teamService.getAlertOnNotFound(redirectAttributes);
        return "redirect:/ipl/admin";
    }
}
