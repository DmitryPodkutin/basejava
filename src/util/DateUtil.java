package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/YYYY");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String dateFormat(LocalDate date) {
        if (date == null) {
            return "";
        }
        return LocalDate.now().equals(date) ? "Сейчас" : DATE_TIME_FORMATTER.format(date);
    }

}
