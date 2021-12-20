package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

//public interface TeamDao extends CrudRepository<Team, Long> {
////    Team findByTeamName(String name);
//
//}
public interface TeamRepository extends JpaRepository<Team, Long> {
    public Team findByName(String name);
}