package com.knoldus.kup.ipl.controllers;
import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Class ResultController.
 */
@Controller
@RequestMapping("result")
public class ResultController {
    /**
     * Injecting Result Service.
     */
    @Autowired
    private ResultService resultService;
//    @Autowired
//    private ProducerService kafkaService;
    /**
     * Injecting UpdateResult Service.
     */
    @Autowired
    private UpdateResultService updateResultService;
    /**
     * Injecting point Service.
     */
    @Autowired
    private PointService pointService;

    /**
     *
     * @param matchId
     * @param model
     * @return add-result
     */
    @GetMapping("/addScore/{matchId}")
    public String showAddForm(@PathVariable ("matchId")
                                  final long matchId, final Model model) {
        resultService.addScores(matchId, model);
        return "add-result";
    }

    /**
     *
     * @param matchId
     * @param model
     * @return update-result
     */
    @GetMapping("/editScore/{match_id}")
    public String showUpdateForm(@PathVariable ("match_id")
                                     final long matchId, final Model model) {
        resultService.editScores(matchId, model);
        return "update-result";
    }

    /**
     *
     * @param id
     * @param match
     * @param model
     * @param redirectAttributes
     * @return admin dashboard
     */
    @PostMapping("/add/{id}")
    public String scoreSave(@PathVariable("id") final long id,
                            final Match match, final Model model,
                            final RedirectAttributes redirectAttributes) {
        resultService.getResult(match);
        pointService.addPointTable(match);
//        kafkaService.sendMatch(match);
        redirectAttributes.addFlashAttribute(
                "message", "Score added successfully");
        redirectAttributes.addFlashAttribute(
                "messageType", "score");
        redirectAttributes.addFlashAttribute(
                "alertType", "success");
        return "redirect:/ipl/admin";
    }

    /**
     *
     * @param id
     * @param match
     * @param model
     * @param redirectAttributes
     * @return admin dashboard
     */
    @PostMapping("/update/{id}")
    public String scoreUpdate(@PathVariable("id") final long id,
                              final Match match, final Model model,
                              final RedirectAttributes redirectAttributes) {
//        kafkaService.sendMatch(match);
        updateResultService.updatePointTable(match, redirectAttributes);
        return "redirect:/ipl/admin";
    }
}