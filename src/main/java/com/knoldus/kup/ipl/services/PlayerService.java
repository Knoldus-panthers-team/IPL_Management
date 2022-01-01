package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    
    @Autowired
    TeamService teamService;
    
    @Autowired
    TeamRepository teamRepository;
    
    @Autowired
    CountryService countryService;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getNewPlayerObject(){
        return new Player();
    }

    public Player savePlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public List<Player> getAllPlayers(){
        return (List<Player>) playerRepository.findAll();
    }

    public Player getPlayerById(Long id){
        return playerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid player id: "+id));
    }

    public Set<Player> getPlayersByTeamId(Long teamId){
        return playerRepository.findByTeamId(teamId);
    }

    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }
    
    public RedirectAttributes getAlertOnSave(Player player, RedirectAttributes redirectAttributes){
        if(player.getTeam() != null) {
//            Team team = teamService.getByName(player.getTeam().getName())
//                    .orElseThrow(() -> new IllegalArgumentException
//                            ("Invalid player name: " + player.getTeam().getName()));
            int playerSize = playerRepository.findByTeamId(player.getTeam().getId()).size();
            if (playerSize < 15) {
                playerRepository.save(player);
                redirectAttributes.addFlashAttribute("message", "Player added successfully");
                this.getSuccessMessage(redirectAttributes);
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Players can not be more than 15");
                redirectAttributes.addFlashAttribute("messageType", "player");
                redirectAttributes.addFlashAttribute("alertType", "error");
            }
        }
        return redirectAttributes;
    }
    
    public Model getPlayerWithModel(Long id, Model model){
        Player player = playerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid player id: "+id));
        List<Team> teams = teamService.getAllTeams();
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("player", player);
        model.addAttribute("teams",teams);
        model.addAttribute("countries",countries);
        return model;
    }
    
    public RedirectAttributes getAlertOnUpdate(Player player, RedirectAttributes redirectAttributes){
        playerRepository.save(player);
        redirectAttributes.addFlashAttribute("message", "Player updated successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getSuccessMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("messageType", "player");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertOnDelete(Long id, RedirectAttributes redirectAttributes){
        playerRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Player deleted successfully");
        redirectAttributes.addFlashAttribute("messageType", "player");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }
    
    public Model getPlayersByTeamIdWithModel(Model model, Long team_id){
        Set<Player> players = this.getPlayersByTeamId(team_id);
        model.addAttribute("players",players);
        model.addAttribute("team", teamService.getTeamById(team_id).orElse(null));
        return model;
    }
}
