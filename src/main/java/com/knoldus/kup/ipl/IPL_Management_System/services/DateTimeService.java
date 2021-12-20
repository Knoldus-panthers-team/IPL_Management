package com.knoldus.kup.ipl.IPL_Management_System.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateTimeService {

    public String formatDateTime(String dateTime){
//        String date2 = "2021-12-15T05:30";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date parsedDate = null;
        try {
            parsedDate = inputFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(parsedDate);
        System.out.println(formattedDate);
        return formattedDate;
    }
}
