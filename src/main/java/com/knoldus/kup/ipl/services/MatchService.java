package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import com.knoldus.kup.ipl.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    /**
     * Match repository  .
     */
    @Autowired
    private final MatchRepository matchRepository;
    /**
     * Venue repo
     */
    @Autowired
    private final VenueRepository venueRepository;
    /**
     * Team repo
     */
    @Autowired
    private final TeamRepository teamRepository;

    /**
     * @param matchRepo
     * @param venueRepo
     * @param teamRepo
     */
    public MatchService(final MatchRepository matchRepo,
                        final VenueRepository venueRepo,
                        final TeamRepository teamRepo) {
        this.matchRepository = matchRepo;
        this.venueRepository = venueRepo;
        this.teamRepository = teamRepo;
    }

    /**
     * @param id
     */
    public void deleteMatch(final Long id) {
        matchRepository.deleteById(id);
    }

    /**
     * @param match
     * @return Saved Match
     */
    public Match saveMatch(final Match match) {
        matchRepository.save(match);
        return match;
    }

    /**
     * @param id
     * @return match details
     */
    public Optional<Match> getMatchById(final Long id) {
        Optional<Match> match = matchRepository.findById(id);
        return match;
    }

    /**
     * @return all match details  .
     */
    public List<Match> getAllMatches() {
        return (List<Match>) matchRepository.findAll();
    }

    /**
     * @return new match  .
     */
    public Match getNewMatch() {
        return new Match();
    }

    /**
     * @param match
     * @return Boolean for slot is booked or not for given match  .
     */
    public boolean isSlotBooked(final Match match) {
        Long selectedVenueId = match.getVenue().getId();
        String selectedDate = match.getMatchDate();
        String selectedTeam1 = match.getTeam1().toString();
        System.out.println(selectedTeam1);
        String selectedTeam2 = match.getTeam2().toString();
        System.out.println(selectedTeam2);
        String dateArr[] = selectedDate.split("\\s");
        List<Match> matches = (List<Match>) matchRepository.findAll();
        List<Match>  dbMatches = matches.stream().filter(
                matchVenue -> matchVenue.getVenue().getId()
                .equals(selectedVenueId)).filter(match1 -> match1.getMatchDate()
                .split("\\s")[0].equals(dateArr[0]))
                .collect(Collectors.toList());
        List<Match>  dbMatches1 = matches.stream().
                filter(match2 -> match2.getTeam1().toString().
                        equals(selectedTeam1) && match2.
                        getTeam2().toString().equals(selectedTeam2))
                .filter(match1 -> match1.getMatchDate()
                        .split("\\s")[0].
                        equals(dateArr[0])).
                collect(Collectors.toList());
        List<Match>  dbMatches2 = matches.stream().
                filter((match2 -> match2.getTeam1().toString().
                        equals(selectedTeam2) && match2.
                        getTeam2().toString().
                        equals(selectedTeam1))).
                filter(match1 -> match1.getMatchDate()
                        .split("\\s")[0].
                        equals(dateArr[0])).collect(Collectors.toList());
        List<Match>  dbMatches3 = matches.stream().
                filter((match2 -> match2.getTeam1().toString().
                equals(selectedTeam1))).filter(match1 -> match1.getMatchDate()
                .split("\\s")[0].equals(dateArr[0])).
                collect(Collectors.toList());
        List<Match>  dbMatches4 = matches.stream().
                filter((match2 -> match2.getTeam1().toString()
                        .equals(selectedTeam2) && match
                        .getTeam2().toString().equals(selectedTeam1)))
                .filter(match1 -> match1.getMatchDate()
                        .split("\\s")[0].equals(dateArr[0])).
                collect(Collectors.toList());
        List<Match>  dbMatches5 = matches.stream()
                .filter((match2 -> match2.getTeam2().toString().
                        equals(selectedTeam1) && match2
                        .getTeam2().toString().equals(selectedTeam1))).
                filter(match1 -> match1.getMatchDate()
                        .split("\\s")[0].equals(dateArr[0]))
                .collect(Collectors.toList());
        List<Match>  dbMatches6 = matches.stream()
                .filter((match2 -> match2.getTeam2().toString().
                        equals(selectedTeam2) && match2.getTeam2()
                        .toString().equals(selectedTeam1))).
                filter(match1 -> match1.getMatchDate()
                        .split("\\s")[0].equals(dateArr[0]))
                .collect(Collectors.toList());
        System.out.println(dbMatches1.size());
        System.out.println(dbMatches1.toString());
        if (dbMatches.size() >= 1) {
            System.out.println("TRUE");
            return true;
        } else if (dbMatches1.size() >= 1) {
            return true;
        } else if (dbMatches2.size() >= 1) {
            return true;
        } else if (dbMatches3.size() >= 1) {
            return true;
        } else if (dbMatches4.size() >= 1) {
            return true;
        } else if (dbMatches5.size() >= 1) {
            return true;
        } else if (dbMatches6.size() >= 1) {
            return true;
        } else {
            System.out.println("FALSE");
            return false;
        }
    }

    /**
     * @param match
     * @return Boolean for the given teams is same or not  .
     */
    public boolean isTeamSame(final Match match) {
        if (match.getTeam1() == match.getTeam2()) {
            return true;
        }
        return false;
    }

    /**
     * @param model
     * @param id
     * @return match details  .
     */
    public Model getMatchDetails(final Model model, final Long id) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("venues", venueRepository.findAll());
        Match match = this.getMatchById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid match id: " + id));
        model.addAttribute("match", match);
        return model;
    }

    /**
     * @param redirectAttributes
     * @param match
     * @return generate alert on saving the match  .
     */
    public RedirectAttributes getAlertOnSave(
            final RedirectAttributes redirectAttributes,
            final Match match) {
        if (isSlotBooked(match) == false) {
            matchRepository.save(match);
            redirectAttributes.addFlashAttribute("message",
                    "Match scheduled successfully");
            this.getSuccessMessage(redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "Match is already Scheduled");
            this.getErrorMessage(redirectAttributes);
        }
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generate success message when match is successfully scheduled  .
     */
    public RedirectAttributes getSuccessMessage(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messageType", "match");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generate error message when match is not scheduled  .
     */
    public RedirectAttributes getErrorMessage(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messageType",
                "match");
        redirectAttributes.addFlashAttribute("alertType",
                "error");
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @param match
     * @return generate alert message when match is successfully rescheduled  .
     */
    public RedirectAttributes getAlertOnUpdate(
            final RedirectAttributes redirectAttributes,
            final Match match) {
        matchRepository.save(match);
        redirectAttributes.addFlashAttribute("message",
                "Match rescheduled successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generate alert if slot is booked or not  .
     */
    public RedirectAttributes getAlertIfSlotBooked(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "message",
                "This slot is booked for other match. "
                        + "Please select another date or venue");
        this.getErrorMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param redirectAttributes
     * @return generate alert if given teams are same  .
     */
    public RedirectAttributes getAlertIfTeamSame(
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                "Teams can't be same");
        this.getErrorMessage(redirectAttributes);
        return redirectAttributes;
    }

    /**
     * @param model
     * @return complete match details  .
     */
    public Model getMatchesWithModel(final Model model) {
        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("match", this.getNewMatch());
        model.addAttribute("matchList", matches);
        return model;
    }

    /**
     * @param redirectAttributes
     * @param id
     * @return generate alert when match is deleted  .
     */
    public RedirectAttributes getAlertOnDelete(
            final RedirectAttributes redirectAttributes,
            final long id) {
        matchRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "message",
                "Match deleted successfully");
        this.getSuccessMessage(redirectAttributes);
        return redirectAttributes;
    }
}
