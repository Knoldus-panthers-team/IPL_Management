package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;
    public void saveTeam(Team team){
        teamRepository.save(team);
    }

    public void updateTeam(Team team){

    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }
    public Team getTeam(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Team Id:" + id));
    }

    public void deleteTeam(Long id){
        teamRepository.delete(this.getTeam(id));
    }
}
