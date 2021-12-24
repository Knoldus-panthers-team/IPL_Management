package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void saveTeam(Team team){
        teamRepository.save(team);
    }

    public Team getNewTeamObject(){
        return new Team();
    }

    public Optional<Team> getTeamById(Long id){
        return teamRepository.findById(id);
    }

    public Optional<Team> getByName(String name){
        return teamRepository.findByName(name);
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public void deleteTeam(Long id){
        teamRepository.delete(this.getTeamById(id).get());
    }
}
