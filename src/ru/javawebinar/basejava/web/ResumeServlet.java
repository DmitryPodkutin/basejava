package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getSqlStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fulName = request.getParameter("fullName");
        final Resume resume;
        boolean safeOrUpdate = uuid.equals("");
        if (safeOrUpdate) {
            resume = new Resume(fulName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fulName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (value == null || value.trim().length() == 0) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(type, new DescriptionSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new ListSection(value.split("\\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        String[] urls = request.getParameterValues(type.name() + "url");
                        List<Organization> organizations = new ArrayList();
                        for (int i = 0; i < values.length; i++) {
                            List<Organization.Position> positions = new ArrayList<>();
                            String counter = type.name() + i;
                            String[] beginDate = request.getParameterValues(counter + "beginDate");
                            String[] endDate = request.getParameterValues(counter + "endDate");
                            String[] namePositions = request.getParameterValues(counter + "position");
                            String[] descriptions = request.getParameterValues(counter + "description");
                            for (int j = 0; j < namePositions.length; j++) {
                                positions.add(new Organization.Position(DateUtil.parseDate(beginDate[j]), DateUtil.parseDate(endDate[j]), namePositions[j], descriptions[j]));
                            }
                            organizations.add(new Organization(new Link(values[i], urls[i]), positions));
                        }
                        resume.addSection(type, new OrganizationSection(organizations));
                        break;
                }
            }
        }
        if (safeOrUpdate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    Section section = resume.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = DescriptionSection.DUMMY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.DUMMY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            if (section == null) {
                                section = OrganizationSection.DUMMY;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + type);
                    }
                    resume.addSection(type, section);
                }
                break;
            case "add":
                resume = Resume.DUMMY;
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                "view".

                        equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp"
        ).

                forward(request, response);
    }
}
