//package com.knoldus.kup.ipl.services;
//
//import com.google.gson.Gson;
//import com.knoldus.kup.ipl.models.Match;
//import com.knoldus.kup.ipl.models.MatchResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class ProducerService {
//
//    @Autowired
//    MatchResult result;
//
//    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
//    private static final String TOPIC = "iplResult";
//
//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
//
//    public String sendDataToKafka(String result) {
//        logger.info(String.format("#### -> Producing message -> %s", result));
//        kafkaTemplate.send(TOPIC, result);
//        return TOPIC;
//    }
//
//    public void sendMatch(Match match){
//        result.setId(match.getId());
//        result.setResult(match.getResult());
//        result.setTeam1Score(match.getTeam1Score());
//        result.setTeam2Score(match.getTeam2Score());
//        result.setTeam1Wickets(match.getTeam1Wickets());
//        result.setTeam2Wickets(match.getTeam2Wickets());
//        result.setTeam1(match.getTeam1().getName());
//        result.setTeam2(match.getTeam2().getName());
//        result.setTeam1Over(match.getTeam1Over());
//        result.setTeam2Over(match.getTeam2Over());
//        result.setVenue(match.getVenue().getName());
//        result.setMatchDate(match.getMatchDate().toString());
//        String gson = new Gson().toJson(result);
//        this.sendDataToKafka(gson);
//    }
//}
