package com.knoldus.kup.ipl.IPL_Management_System.services;

import com.knoldus.kup.ipl.IPL_Management_System.models.Match;
import com.knoldus.kup.ipl.IPL_Management_System.models.Team;
import com.knoldus.kup.ipl.IPL_Management_System.models.Venue;
import com.knoldus.kup.ipl.IPL_Management_System.repository.MatchRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.TeamRepository;
import com.knoldus.kup.ipl.IPL_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    TeamRepository teamRepository;

    public String addForm(Model model){
        List<Venue> venues= (List<Venue>) venueRepository.findAll();
        model.addAttribute("venues",venues);
        List<Team> teams = (List<Team>) teamRepository.findAll();
        model.addAttribute("teams",teams);
        Match match = new Match();
        match.setMatchWinner("NA");
        match.setResult("NA");
        match.setResult("NA");
        model.addAttribute("match",match);
        return "add-match";
    }

    public void saveMatch(Match match){
//        if (match.getTeam1Over() == null && match.getTeam2Over()==null){
//            match.setTeam1Over("Yet to bat");
//            match.setTeam2Over("Yet to bat");
//        }
        matchRepository.save(match);
    }

    public String editMatchForm(long id, Model model){
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match Id:" + id));
        List<Venue> venues= (List<Venue>) venueRepository.findAll();
        model.addAttribute("venues",venues);

        List<Team> teams = (List<Team>) teamRepository.findAll();
        model.addAttribute("teams",teams);

        model.addAttribute("match", match);
        return "update-match";
    }

    @Transactional
    public Optional<Match> getTeam1(Team team){
        return matchRepository.findByTeam1(team);
    }
    public boolean isTeam1Exist(Team team1, BindingResult result){
        try{
            return getTeam1(team1).isPresent();
        }catch (Exception ex){
            result.addError(new FieldError("match", "team1", "Team already exist"));
        }
        return false;
    }
    @Transactional
    public Optional<Match> getTeam2(Team team2){
        return matchRepository.findByTeam2(team2);
    }

    public boolean isTeam2Exist(Team team2, BindingResult result){
        try{
            return getTeam1(team2).isPresent();
        }catch (Exception ex){
            result.addError(new FieldError("match", "team2", "Team already exist"));
        }
        return false;
    }
    @Transactional
    public Optional<Match> getScheduleDate(String date){
        Optional<Match> match= matchRepository.findByMatchDate(date);
        System.out.println(match.get().getMatchDate()+"venu: "+match.get().getVenue());
        return match;
    }
    public boolean isDatePresent(String date, BindingResult result){
        try{
            return getScheduleDate(date).isPresent();
        }catch (Exception ex){
            result.addError(new FieldError("match", "matchDate", "Schedule date already exist"));
        }
        return false;
    }
    @Transactional
    public Optional<Match> getVenue(Long id){
        Optional<Match> match= matchRepository.findByVenueId(id);
        System.out.println("getve : "+match.get().getMatchDate()+"venu: "+match.get().getVenue());
        return match;
    }
    public boolean isVenuePresent(Long id, BindingResult result){
        try{
            return getVenue(id).isPresent();
        }catch (Exception ex){
            ex.printStackTrace();
            result.addError(new FieldError("match", "venue", "This slot is booked for other match. Please select another date or venue"));
        }
        return false;
    }
}
