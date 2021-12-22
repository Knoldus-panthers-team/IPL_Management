package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getNewPlayerObject(){
        return new Player();
    }

    public void savePlayer(Player player){
        playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(Long id){
        return playerRepository.findById(id);
    }

    public Optional<Set<Player>> getPlayersByTeamId(Long team_id){
        return playerRepository.findByTeamId(team_id);
    }

    public void deletePlayer(Long id){
        playerRepository.delete(this.getPlayerById(id).get());
    }
}
