package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.models.Player;

import com.knoldus.kup.ipl.services.CountryService;
import com.knoldus.kup.ipl.services.PlayerService;
import com.knoldus.kup.ipl.services.TeamService;
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


@Controller
@RequestMapping("/players")
public class PlayerController {
    /**
     * Injecting player service.
     */
    @Autowired
    private PlayerService playerService;
    /**
     * Injecting team service.
     */
    @Autowired
    private TeamService teamService;
    /**
     * Injecting country service.
     */
    @Autowired
    private CountryService countryService;

//    @GetMapping("/addForm")
//    public String addForm(Model model){
//        model.addAttribute("player", playerService.getNewPlayerObject());
//        return "add-player";
//    }
    /**
     * @param player
     * @param bindingResult
     * @param redirectAttributes
     * @return admin dashboard
     */
    @PostMapping("/add")
    public String addPlayer(final @Valid Player player,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
        { return "addPlayer"; }
        else { playerService.getAlertOnSave(player,redirectAttributes); }
        return "redirect:/ipl/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        playerService.getPlayerWithModel(id,model);
        return "update-player";
    }

    @PostMapping("/update/{id}")
    public String updatePlayer(@PathVariable("id") long id, @Valid Player player,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-player";
        }
        playerService.getAlertOnUpdate(player, redirectAttributes);
        return "redirect:/ipl/admin";
    }
    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        playerService.getAlertOnDelete(id, redirectAttributes);
        return "redirect:/ipl/admin";
    }
}
