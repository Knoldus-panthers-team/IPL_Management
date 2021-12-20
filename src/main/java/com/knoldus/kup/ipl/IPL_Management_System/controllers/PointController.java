package com.knoldus.kup.ipl.IPL_Management_System.controllers;

import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.PointTable;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.PointRepository;
import com.knoldus.kup.ipl.IPL_Management_System.services.PointService;
import com.knoldus.kup.ipl.IPL_Management_System.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PointController {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    ResultService resultService;
    @Autowired
    PointService pointService;




//    @PostMapping("/save")
//    public String addScore(PointTable pointTable){
//        pointRepository.save(pointTable);
//        return "redirect:/ipl/admin";
//    }
//
//    @GetMapping("/edit/{match_id}")
//    public String showUpdateForm(@PathVariable ("match_id") long match_id, Model model) {
////        PointTable pointTable = pointRepository.findById(pointTable_id)
////                .orElseThrow(() -> new IllegalArgumentException("Invalid pointTable Id:" + pointTable_id));
//
//        Match match = matchRepository.findById(match_id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + match_id));
//
////        model.addAttribute("pointTable", pointTable);
//        model.addAttribute("match",match);
//        return "update-result";
//    }
//
//    @PostMapping("/update")
//    public String matchUpdate(@PathVariable long id, Match match, Model model){
////        resultService.getResult(match);
////        System.out.println("Runnig ----------------"+match.getResult());
//        pointService.updatePointTable(match);
//        return "redirect:/ipl/admin";
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String deletePointTable(@PathVariable("id") long id, Model model) {
//        PointTable table = pointRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + id));
//        pointRepository.delete(table);
//        return "redirect:/ipl/admin";
//    }
}


//public class PointTable
//