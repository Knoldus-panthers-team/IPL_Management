package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    
    @Autowired
    TeamService teamService;

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
    
    public RedirectAttributes getSuccessAlertOnSave(Player player, RedirectAttributes redirectAttributes){
        if(teamService.getByName(player.getTeam().getName()).isPresent()) {
            Team team = teamService.getByName(player.getTeam().getName())
                    .orElseThrow(() -> new IllegalArgumentException
                            ("Invalid player name: " + player.getTeam().getName()));
            if (team.getPlayers().size() < 3) {
                playerRepository.save(player);
                redirectAttributes.addFlashAttribute("message", "Player added successfully");
                redirectAttributes.addFlashAttribute("messageType", "player");
                redirectAttributes.addFlashAttribute("alertType", "success");
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Players can not be more than 15");
                redirectAttributes.addFlashAttribute("messageType", "player");
                redirectAttributes.addFlashAttribute("alertType", "error");
            }
        }
        return redirectAttributes;
    }
}
