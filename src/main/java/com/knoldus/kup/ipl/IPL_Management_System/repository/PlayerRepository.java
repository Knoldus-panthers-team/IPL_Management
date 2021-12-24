package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Set<Player> findByTeamId(Long team_id);
}
