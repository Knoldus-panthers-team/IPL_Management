//package com.knoldus.kup.ipl.IPL_Management_System.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class TestController {
//
//    @GetMapping("/suggest-event")
//    public String suggestEvent(@RequestParam(value = "message", required = false) String message, Model model) {
//        model.addAttribute("message",message);
//        return "suggestEvent";
//    }
//
//    @PostMapping("/suggest-event")
//    public String receiveSuggestedEvent( RedirectAttributes redirectAttributes) {
//        redirectAttributes.addAttribute("message", "Success");
//        return "redirect:/suggest-event";
//    }
//}
