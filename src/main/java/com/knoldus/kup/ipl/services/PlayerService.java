package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    /**
     * Player Repository  .
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Team Service  .
     */
    @Autowired
    private TeamService teamService;

    /**
     * team Repository  .
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * Country Service  .
     */
    @Autowired
    private CountryService countryService;

    /**
     * @param playerRepo
     */
    public PlayerService(final PlayerRepository playerRepo) {
        this.playerRepository = playerRepo;
    }

    /**
     * @return new Player  .
     */
    public Player getNewPlayerObject() {
        return new Player();
    }

    /**sitory
     * @param player
     * @return Player on saving successfully  .
     */
    public Player savePlayer(final Player player) {
        playerRepository.save(player);
        return player;
    }

    /**
     * @return list of all Players  .
     */
    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    /**
     * @param id
     * @return get Player details by Id  .
     */
    public Player getPlayerById(final Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                                "Invalid player id: " + id));
    }

    /**
     * @param teamId
     * @return get Player details by teamId  .
     */
    public Set<Player> getPlayersByTeamId(final Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
    /**
     * @param id
     * @return delete Player by Id  .
     */
    public void deletePlayer(final Long id) {
        playerRepository.deleteById(id);
    }

    /**
     * @param player
     * @param redirectAttributes
     * @return generates alert on successfully saving Player  .
     */
    public RedirectAttributes getAlertOnSave(
            final Player player,
            final RedirectAttributes redirectAttributes) {
        if (player.getTeam() != null) {
//            Team team = teamService.getByName(player.getTeam().getName())
//                    .orElseThrow(() -> new IllegalArgumentException
//                            ("Invalid player name: "
//                            + player.getTeam().getName()));
            int playerSize = playerRepository
                    .findByTeamId(player.getTeam().getId()).size();
            if (playerSize < 15) {
                playerRepository.save(player);
                redirectAttributes.addFlashAttribute(
                        "message",
                        "Player added successfully");
                this.getSuccessMessage(redirectAttributes);
            } else {
                redirectAttributes.addFlashAttribute(
                        "message",
                        "Players can not be more than 15");
                redirectAttributes.addFlashAttribute(
                        "messageType",
                        "player");
                redirectAttributes.addFlashAttribute(
                        "alertType",
                        "error");
            }
        }
        return redirectAttributes;
    }

    /**
     * @param id
     * @param model
     * @return Player  .
     */
    public Model getPlayerWithModel(
            final Long id,
            final Model model) {
        Player player = playerRepository
                .findById(id).orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid player id: " + id));
        List<Team> teams = teamService.getAllTeams();
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("player", player);
        model.addAttribute("teams", teams);
        model.addAttribute("countries", countries);
        return model;
    }

    /**
     * @param player
     * @param redirectAttributes
     * @return generates alert on updating the Player  .
     */
    public RedirectAttributes getAlertOnUpdate(
            final Player player,
            final RedirectAttributes redirectAttributes) {
        playerRepository.save(player);
        redirectAttributes.addFlashAttribute(
                "message",
                "Player updated successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generates success message  .
     */
    public RedirectAttributes getSuccessMessage(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "messageType",
                "player");
        redirectAttributes.addFlashAttribute(
                "alertType",
                "success");
        return redirectAttributes;
    }

    /**
     * @param id
     * @param redirectAttributes
     * @return genertae alert on saving Player  .
     */
    public RedirectAttributes getAlertOnDelete(
            final Long id,
            final RedirectAttributes redirectAttributes) {
        playerRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "message",
                "Player deleted successfully");
        redirectAttributes.addFlashAttribute(
                "messageType",
                "player");
        redirectAttributes.addFlashAttribute(
                "alertType",
                "success");
        return redirectAttributes;
    }

    /**
     * @param model
     * @param teamId
     * @return Player by given Id  .
     */
    public Model getPlayersByTeamIdWithModel(
            final Model model,
            final Long teamId) {
        Set<Player> players = this.getPlayersByTeamId(teamId);
        model.addAttribute(
                "players", players);
        model.addAttribute(
                "team",
                teamService.getTeamById(teamId).orElse(null));
        return model;
    }
}
