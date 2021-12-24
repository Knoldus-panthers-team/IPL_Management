//package com.knoldus.kup.ipl.IPL_Management_System.controllers;
//
//import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
//import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
//import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//public class ScoreBoardController {
//
//    @Autowired
//    MatchRepository matchRepository;
//
//    @Autowired
//    TeamRepository teamRepository;
//
//    @RequestMapping("/score")
//    public String getScoreBoard(Model model){
//
//        List<Match> matches = (List<Match>) matchRepository.findAll();
//        List<Team> teams = (List<Team>) teamRepository.findAll();
//
////        List<Match> matches1 = (List<Match>) teams.get(0).getTeam1Maches();
//
//        teams.stream().map(team->team.getTeam1Matches().stream().map(match -> match.getTeam1Score())).forEach(System.out::println);
//
//        model.addAttribute("teams",teams);
//
//        return "score-board";
//    }
//
//}
