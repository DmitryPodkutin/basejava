package ru.javawebinar.basejava;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class MainDate {
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        Date date = new Date();
//        System.out.println(date);
//        System.out.println(System.currentTimeMillis() - start);
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        System.out.println(cal.getTime());

        LocalDate ld = LocalDate.of(1983,7,24);
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
     //   System.out.println(ldt);


//        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
//        System.out.println(sdf.format(date));


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/YYYY");
        System.out.println(dtf.format(ld));


    }
}