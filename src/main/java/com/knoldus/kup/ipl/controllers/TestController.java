//package com.knoldus.kup.ipl.controllers;
//
//import com.knoldus.kup.ipl.services.ProducerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class TestController {
//
////    @GetMapping("/suggest-event")
////    public String suggestEvent(@RequestParam(value = "message", required = false) String message, Model model) {
////        model.addAttribute("message",message);
////        return "suggestEvent";
////    }
////
////    @PostMapping("/suggest-event")
////    public String receiveSuggestedEvent( RedirectAttributes redirectAttributes) {
////        redirectAttributes.addAttribute("message", "Success");
////        return "redirect:/suggest-event";
////    }
//
//    @Autowired
//    final ProducerService producerService;
//
//    public TestController(ProducerService producerService) {
//        this.producerService = producerService;
//    }
//
//    @GetMapping("/sending/{message}")
//    public String sendMessageToKafka(@PathVariable("message") String message){
//        System.out.println("Sending.................................."+message);
//        return this.producerService.sendMessage(message);
//    }
//}
