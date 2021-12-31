//package com.knoldus.kup.ipl.serializer;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.knoldus.kup.ipl.models.MatchResult;
//import org.apache.kafka.common.serialization.Serializer;
//
//public class MatchResultSerializer implements Serializer<MatchResult> {
//
//    @Override
//    public byte[] serialize(String s, MatchResult matchResult) {
//        byte[] userToByte = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            userToByte = objectMapper.writeValueAsString(matchResult).getBytes();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        return userToByte;
//        return new byte[0];
//    }
//}
