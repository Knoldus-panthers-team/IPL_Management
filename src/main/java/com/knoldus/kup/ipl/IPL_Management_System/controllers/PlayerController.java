package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.dao.PlayerDao;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
@RequestMapping("ipl")
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;

    @GetMapping("/table")
    public String getAllPlayers(Model model){
        Player player = new Player();
        List<Player> players = (List<Player>) playerDao.findAll();
        model.addAttribute("players",players);
        model.addAttribute("player",player);
        return "index";
    }

    @GetMapping("/new")
    public String addPlayerForm(Model model){
        Player player = new Player();
        model.addAttribute("player",player);
        return "addPlayer";
    }

    @GetMapping("/add")
    public String addPlayer(Player player){
        playerDao.save(player);
        return "/redirect:/";
    }
}
