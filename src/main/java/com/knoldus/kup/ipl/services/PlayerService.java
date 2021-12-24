package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
