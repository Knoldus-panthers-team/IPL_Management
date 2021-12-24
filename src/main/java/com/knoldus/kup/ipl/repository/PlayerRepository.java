package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Set<Player> findByTeamId(Long team_id);
}
