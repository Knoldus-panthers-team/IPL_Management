//package com.knoldus.kup.ipl.Producer;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//public class Producer {
//
//    @GetMapping("/publish/{message}")
//    public String sendMessageToKafkaTopic(@PathVariable("message") String message) {
//        consumerService.addMessage(message);
//        return this.producer.sendMessage(message);
//    }
//}
