package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MatchRepository extends CrudRepository<Match, Long> {

    public Optional<Match> findByVenueId(Long id);

    public Optional<Match> findByMatchDate(String date);

    public Optional<Match> findByTeam1(Team team);

    public Optional<Match>findByTeam2(Team team2);
}
