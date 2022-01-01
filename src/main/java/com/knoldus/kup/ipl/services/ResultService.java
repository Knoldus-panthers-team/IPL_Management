package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.models.Venue;
import com.knoldus.kup.ipl.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    MatchRepository matchRepository;
    
    @Autowired
    TeamService teamService;
    
    @Autowired
    VenueService venueService;
    
    @Autowired
    MatchService matchService;
    
    @Autowired
    private PointService pointService;
    
//    @Autowired
//    private ProducerService kafkaService;
    
    @Autowired
    private UpdateResultService updateResultService;

    public void getResult(Match match)
    {
        int team1Runs = Integer.parseInt(match.getTeam1Score());
        int team2Runs = Integer.parseInt(match.getTeam2Score());
        int team1Wickets = Integer.parseInt(match.getTeam1Wickets());
        int team2Wickets = Integer.parseInt(match.getTeam2Wickets());

        String result;
        String teamWinner;
        Team tossWinner = match.getTossWinnerTeam();
        String tossChoice = match.getTossChoice();
        Long firstInningsTeam = 1L;

        if(tossWinner.getId().equals(match.getTeam1().getId())){
            if(tossChoice.equals("batting")){
                firstInningsTeam = match.getTeam1().getId();
            }else{
                firstInningsTeam = match.getTeam2().getId();
            }
        }else if(tossWinner.getId().equals(match.getTeam2().getId())){
            if(tossChoice.equals("batting")){
                firstInningsTeam = match.getTeam2().getId();
            }else{
                firstInningsTeam = match.getTeam1().getId();
            }
        }

        if(team1Runs>team2Runs){
            teamWinner = match.getTeam1().getName();
            if(firstInningsTeam.equals(match.getTeam1().getId())){         //won by runs
                result = teamWinner+" won by "+(team1Runs-team2Runs)+" runs";
            }else { //won by wickets
                result = teamWinner+" won by "+(10-team1Wickets)+" wickets";
            }
        }
        else {
            teamWinner = match.getTeam2().getName();
            if(firstInningsTeam.equals(match.getTeam2().getId())){       //won by runs
                result = teamWinner+" won by "+(team2Runs-team1Runs)+" runs";
            }else { //won by wickets
                result=teamWinner+" won by "+(10-team2Wickets)+" wickets";
            }
        }
        match.setMatchWinner(teamWinner);
        match.setResult(result);
        matchRepository.save(match);
    }
    
    public Model getScoreForm(Model model, Long matchId){
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + matchId));
    
        if (match.getTeam1Over().equals("Yet to be played")) {
            match.setTeam1Over("");
            match.setTeam2Over("");
        }
        model.addAttribute("match",match);
    
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
    
        List<Venue> venues= venueService.getAllVenues();
        model.addAttribute("venues",venues);
        return model;
    }
    
    public Model getEditScoreForm(Long matchId, Model model){
        Match match = matchService.getMatchById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + matchId));
        model.addAttribute("match",match);
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams",teams);
        List<Venue> venues= venueService.getAllVenues();
        model.addAttribute("venues",venues);
        model.addAttribute("team1", match.getTeam1().getName());
        model.addAttribute("team2", match.getTeam2().getName());
        return model;
    }
    
    public RedirectAttributes getAlertOnSave(Match match, RedirectAttributes redirectAttributes){
        this.getResult(match);
        pointService.addPointTable(match);
//        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute("message", "Score added successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertOnUpdate(RedirectAttributes redirectAttributes, Match match){
        updateResultService.updatePointTable(match);
//        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute("message", "Score updated successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getSuccessMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("messageType", "score");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }
}
