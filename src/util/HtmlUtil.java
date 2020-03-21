package util;

import ru.javawebinar.basejava.model.Link;
import ru.javawebinar.basejava.model.Organization;



public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String dateFormat(Organization.Position position) {
        return DateUtil.dateFormat(position.getBeginDate()) + "-" + DateUtil.dateFormat(position.getEndDate());
    }

    public static String urlFormat(Link homePage) {
        String url = homePage.getUrl();
        String name = homePage.getName();
        return (url == null || url.equals("")) ? "<a>" + name + "</a>" : "<a href=" + url + ">" + name + "</a>";
    }
}
