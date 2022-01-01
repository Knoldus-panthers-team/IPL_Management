package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import com.knoldus.kup.ipl.repository.VenueRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {

//    Match repository
    @Autowired
    private final MatchRepository matchRepository;
    // Venue repo
    @Autowired
    private final VenueRepository venueRepository;

//    Team repo
    @Autowired
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository,
                        VenueRepository venueRepository,
                        TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.venueRepository = venueRepository;
        this.teamRepository = teamRepository;
    }
    public void deleteMatch(final Long id){
        matchRepository.deleteById(id);
    }

    public Match saveMatch(final Match match){
        matchRepository.save(match);
        return match;
    }

    public Optional<Match> getMatchById(Long id){
        Optional<Match> match = matchRepository.findById(id);
        return match;
    }
    public List<Match> getAllMatches(){
        return (List<Match>) matchRepository.findAll();
    }

    public Match getNewMatch(){return new Match();}

    public boolean isSlotBooked(Match match){
        Long selectedVenueId = match.getVenue().getId();
        String selectedDate = match.getMatchDate();
        String dateArr[] = selectedDate.split("\\s");
        List<Match> matches = (List<Match>) matchRepository.findAll();
        List<Match>  dbMatches = matches.stream().filter(matchVenue -> matchVenue.getVenue().getId()
                .equals(selectedVenueId)).filter(match1 -> match1.getMatchDate()
                .split("\\s")[0].equals(dateArr[0])).collect(Collectors.toList());
        if(dbMatches.size()>=1){
            if(dbMatches.get(0).getId().equals(match.getId()))
                return false;
            return true;
        }
        return false;
    }
    public boolean isTeamSame(Match match){
        if (match.getTeam1() == match.getTeam2()) {
            return true;
        }
        return false;
    }

    public Model getMatchDetails(Model model, Long id){
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("venues",venueRepository.findAll());
        Match match = this.getMatchById(id).orElseThrow(()->new IllegalArgumentException("Invalid match id: "+id));
        model.addAttribute("match",match);
        return model;
    }
    
    public RedirectAttributes getAlertOnSave(RedirectAttributes redirectAttributes, Match match){
        matchRepository.save(match);
        redirectAttributes.addFlashAttribute("message", "Match scheduled successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getSuccessMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }
    public RedirectAttributes getErrorMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "error");
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertOnUpdate(RedirectAttributes redirectAttributes, Match match){
        matchRepository.save(match);
        redirectAttributes.addFlashAttribute("message", "Match rescheduled successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertIfSlotBooked(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",
                "This slot is booked for other match. " +
                        "Please select another date or venue");
        this.getErrorMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public RedirectAttributes getAlertIfTeamSame(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Teams can't be same");
        this.getErrorMessage(redirectAttributes);
        return redirectAttributes;
    }
    
    public Model getMatchesWithModel(Model model){
        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("match",this.getNewMatch());
        model.addAttribute("matchList",matches);
        return model;
    }
    
    public RedirectAttributes getAlertOnDelete(RedirectAttributes redirectAttributes,long id){
        matchRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Match deleted successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
}
