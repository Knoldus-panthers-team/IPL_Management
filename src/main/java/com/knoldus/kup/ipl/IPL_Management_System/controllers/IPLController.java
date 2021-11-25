package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.dao.PlayerDao;
import com.knoldus.kup.ipl.IPL_Management_System.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class IPLController {


//    ---------------------- Player Part---------------------------------
    @Autowired
    PlayerDao playerDao;


    @GetMapping("/table")
    public String getAllPlayers(Model model){
        Player player = new Player();
        List<Player> players = (List<Player>) playerDao.findAll();
        model.addAttribute("players",players);
        model.addAttribute("player",player);
        return "index";
    }

    @PostMapping("/add")
    public RedirectView addPlayer(Player player){
        playerDao.save(player);
        return new RedirectView("/table");
//        return "/redirect:table";
    }
//    ----------------------------------------------------------------------

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}
