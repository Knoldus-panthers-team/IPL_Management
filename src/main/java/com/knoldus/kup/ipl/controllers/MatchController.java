package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * MatchController.
 */
@Controller
@RequestMapping("/matches")
public class MatchController {
    /**
     * Injecting match repository.
     */
    @Autowired
    private MatchRepository matchRepository;
    /**
     * Injecting match service.
     */
    @Autowired
    private MatchService matchService;

    /**
     *
     * @param match
     * @param bindingResult
     * @param redirectAttributes
     * @return add-match
     */
    @PostMapping("/save")
    public String saveMatch(final @Valid Match match,
                            final BindingResult bindingResult,
                            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-match";
        }
        matchService.getAlertOnSave(redirectAttributes, match);
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param id
     * @param model
     * @return update-match
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id")
                                   final long id, final Model model) {
        matchService.getMatchDetails(model, id);
        return "update-match";
    }

    /**
     *
     * @param id
     * @param match
     * @param model
     * @param bindingResult
     * @param redirectAttributes
     * @return add match
     */
    @PostMapping("/update/{id}")
    public String matchUpdate(@PathVariable final long id,
                              final Match match, final Model model,
                              final BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-match";
        }
        if (matchService.isSlotBooked(match)) {
            matchService.getAlertIfSlotBooked(redirectAttributes);
            return "redirect:/matches/edit/" + match.getId();
        }
        if (matchService.isTeamSame(match)) {
            matchService.getAlertIfTeamSame(redirectAttributes);
            return "redirect:/matches/edit/" + match.getId();
        }
        matchService.getAlertOnUpdate(redirectAttributes, match);
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param model
     * @return match-details
     */
    @GetMapping("/list")
    public String getMatches(final Model model) {
        matchService.getMatchesWithModel(model);
        return "match-details";
    }

    /**
     *
     * @param id
     * @param model
     * @param redirectAttributes
     * @return admin page
     */
    @GetMapping("/delete/{id}")
    public String deleteMatch(@PathVariable("id")
                                  final long id, final Model model,
                              final RedirectAttributes redirectAttributes) {
        matchService.getAlertOnDelete(redirectAttributes, id);
        return "redirect:/ipl/admin";
    }

}
