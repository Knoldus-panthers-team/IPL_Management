//package com.knoldus.kup.ipl.controllers;
//
//import com.google.gson.Gson;
//import com.knoldus.kup.ipl.models.Match;
//import com.knoldus.kup.ipl.models.MatchResult;
//import com.knoldus.kup.ipl.repository.MatchRepository;
//import com.knoldus.kup.ipl.services.ProducerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/app")
//public class KafkaController {
//
//    @Autowired
//    ProducerService producerService;
//
//    @Autowired
//    MatchRepository repository;
//
//    @GetMapping(value = "/kafka")
//    public String postJsonMessage(MatchResult result){
//        result.setId(2L);
//        result.setResult("KKR won by 222");
//        result.setTeam1Score("124");
//        result.setTeam1Wickets("8");
//        result.setVenue("Delhi");
//        result.setMatchDate("2021-12-28 08:00 PM");
//        String gson = new Gson().toJson(result);
//        System.out.println("Hello......................"+result);
//         producerService.sendDataToKafka(gson);
//
//        return "Successfully getting";
//
//    }
////    @GetMapping("/sending/{message}")
////        public String sendMessageToKafka(@PathVariable("message") String message){
////        System.out.println("Sending.................................."+message);
////        return this.producerService.sendMessage(message);
////    }
//}
