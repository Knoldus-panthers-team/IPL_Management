package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//public interface TeamDao extends CrudRepository<Team, Long> {
////    Team findByTeamName(String name);
//
//}
public interface TeamRepository extends CrudRepository<Team, Long> {
    public Optional<Team> findByName(String name);
}