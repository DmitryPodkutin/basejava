package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/YYYY");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_HTML = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate parseDate(String date) {
        if (date.equals("") || date.equals("Сейчас")) {
            return LocalDate.now();
        } else {
            return LocalDate.parse("1/" + date, DATE_TIME_FORMATTER_FOR_HTML);
        }
    }

    public static String dateFormat(LocalDate date) {
        if (date == null) {
            return "";
        }
        return LocalDate.now().equals(date) ? "Сейчас" : DATE_TIME_FORMATTER.format(date);
    }

}
