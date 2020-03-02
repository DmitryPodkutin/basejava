package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.SqlStorage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pr = response.getWriter();
        SqlStorage sqlStorage = (SqlStorage) Config.get().getSqlStorage();

        pr.println("<html>" +
                "<head>" +
                "<title> Resumes </title>" +
                "<style type=\"text/css\"> b {color:white;background-color:#525D76;}</style>"
                + "</head>" +
                "<body>" +
                "<table border=\"0\">" +
                "<td>UUID</td><td>FULL_NAME</td>");
        sqlStorage.getAllSorted().forEach(resume -> {
            pr.println("<tr>");
            pr.println("<td>" + "<b>" + resume.getUuid() + "</b></td> " +
                    "<td><b>" + resume.getFullName() + "</b></td>");
            pr.println("</tr>");
        });
        pr.println("</table>" +
                "</body>" +
                "</html>");
    }
}
