package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Dmitry Podkutin");
        resume.getContacts().put(ContactType.TEL, "2952321");
        resume.getContacts().put(ContactType.SKYPE, "None");
        resume.getContacts().put(ContactType.EMAIL, "dmitry.podkutin@gmail.com");
        resume.getContacts().put(ContactType.LINKED_IN, "None");
        resume.getContacts().put(ContactType.GIT_HUB, "https://github.com/DmitryPodkutin");
        resume.getContacts().put(ContactType.STACKOVER_FLOW, "None");
        resume.getContacts().put(ContactType.HOME_PAGE, "None");

        resume.getSections().put(SectionType.OBJECTIVE, new DescriptionSection("POSITION"));
        resume.getSections().put(SectionType.PERSONAL, new DescriptionSection("PERSONAL_QUALITIES"));
        resume.getSections().put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("ACHIEVEMENT_1", "ACHIEVEMENT_2", "ACHIEVEMENT_3")));
        resume.getSections().put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("QUALIFICATIONS_1", "QUALIFICATIONS_2", "QUALIFICATIONS_3")));
        resume.getSections().put(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(new Organization(
                "WORK_PLASE", "www.workplase.com",
                LocalDate.ofYearDay(2018, 23)
                , LocalDate.ofYearDay(2019, 3),
                "WORKET_AT_WORK"))));
        resume.getSections().put(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(new Organization(
                "PLASE_OF_STUDY",
                "www.stadyplase.com",
                LocalDate.ofYearDay(2008, 24),
                LocalDate.ofYearDay(2010, 1),
                "STUDY_AT_THE_INSTITUTE"))));

        System.out.print(resume);
        System.out.print(resume.getContacts().toString());
        System.out.print(resume.getSections().toString());


    }
}

