package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static final long MSEC_SINCE_EPOCH = System.currentTimeMillis();

    public static String getStatusWithTime() {
        // Convert the milliseconds into a
        // java.util.Date object.
        Date instant = new Date(MSEC_SINCE_EPOCH);
        System.out.println(instant);

        String yourString = "2021-12-15T05:30";

        String date = yourString.substring(0, 10);
        String time = yourString.substring(12, 16);
        System.out.println("After "+date+" "+time);


        // Set up a simple date format, to give the view
        // of the date object that we want.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");

        // Define the String, time, to be our formatted
//        String time = sdf.format(instant);
//        return "Running, " + time;

        String date2 = "2021-12-15T05:30";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date parsedDate = null;
        try {
            parsedDate = inputFormat.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(parsedDate);
        System.out.println(formattedDate);
        return "";
    }

    public static void main(String[] args) {
        System.out.println();
        Test.getStatusWithTime();

    }
}
