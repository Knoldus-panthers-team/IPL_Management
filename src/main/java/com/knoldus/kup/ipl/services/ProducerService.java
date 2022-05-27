package com.knoldus.kup.ipl.services;

import com.google.gson.Gson;
import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    /**
     * Match Result  .
     */
    @Autowired
    private MatchResult matchResult;

    /**
     * Logger  .
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PlayerService.class);
    /**
     * Topic name for Kafka  .
     */
    private static final String TOPIC = "iplResult";

    /**
     * Kafka Template  .
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @param result
     * @return Topic  .
     */
    public String sendDataToKafka(final String result) {
        LOGGER.info(String.format("#### -> Producing message -> %s", result));
        kafkaTemplate.send(TOPIC, result);
        return TOPIC;
    }

    /**
     * @param match
     */
    public void sendMatch(final Match match) {
        matchResult.setId(match.getId());
        matchResult.setResult(match.getResult());
        matchResult.setTeam1Score(match.getTeam1Score());
        matchResult.setTeam2Score(match.getTeam2Score());
        matchResult.setTeam1Wickets(match.getTeam1Wickets());
        matchResult.setTeam2Wickets(match.getTeam2Wickets());
        matchResult.setTeam1(match.getTeam1().getName());
        matchResult.setTeam2(match.getTeam2().getName());
        matchResult.setTeam1Over(match.getTeam1Over());
        matchResult.setTeam2Over(match.getTeam2Over());
        matchResult.setVenue(match.getVenue().getName());
        matchResult.setMatchDate(match.getMatchDate().toString());
        String gson = new Gson().toJson(matchResult);
        this.sendDataToKafka(gson);
    }
}
