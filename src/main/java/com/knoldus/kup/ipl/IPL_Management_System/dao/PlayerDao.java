package com.knoldus.kup.ipl.IPL_Management_System.dao;

import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerDao extends CrudRepository<Player, Long> {
}
