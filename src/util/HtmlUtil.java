package util;

import ru.javawebinar.basejava.model.Link;

import java.time.LocalDate;

public class HtmlUtil {
    public static String dateFormat(LocalDate date) {
        return DateUtil.dateFormat(date);
    }

    public static String urlFormat(Link homePage) {
        String url = homePage.getUrl();
        String name = homePage.getName();
        return (url == null || url.equals("")) ? "<a>" + name + "</a>" : "<a href=" + url + ">" + name + "</a>";
    }
}
