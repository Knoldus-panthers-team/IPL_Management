package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MatchRepository extends CrudRepository<Match, Long> {

    public Optional<Match> findByVenueId(Long id);

    public Optional<Match> findByMatchDate(String date);

    public Optional<Match> findByTeam1(Team team);

    public Optional<Match>findByTeam2(Team team2);
}
