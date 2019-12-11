package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.getContacts().put(ContactType.TEL, "+7(921) 855-0482");
        resume.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        resume.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.getContacts().put(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        resume.getContacts().put(ContactType.GIT_HUB, "https://github.com/gkislin");
        resume.getContacts().put(ContactType.STACKOVER_FLOW, "https://stackoverflow.com/users/548473");
        resume.getContacts().put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        resume.getSections().put(SectionType.OBJECTIVE, new DescriptionSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.getSections().put(SectionType.PERSONAL, new DescriptionSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.getSections().put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.", "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С," +
                        " Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера.")));
        resume.getSections().put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle")));
        resume.getSections().put(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(
                new Organization(
                        "Java Online Projects", "http://javaops.ru/",
                        LocalDate.of(2013, 10, 1)
                        , LocalDate.now(),
                        "Автор проекта. " +
                                "Создание, организация и проведение Java онлайн проектов и стажировок."),
                new Organization(
                        "Wrike", "https://www.wrike.com/",
                        LocalDate.of(2011, 10, 1)
                        , LocalDate.of(2016, 1, 1),
                        "Старший разработчик (backend)\n" +
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO"),
                new Organization(
                        "RIT Center", " ",
                        LocalDate.of(2012, 4, 1)
                        , LocalDate.of(2014, 10, 1),
                        "Java архитектор\n" +
                                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                                "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                                "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")
        )));
        resume.getSections().put(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(
                new Organization(
                        "Coursera",
                        "https://www.coursera.org/course/progfun",
                        LocalDate.of(2013, 3, 1),
                        LocalDate.ofYearDay(2013, 5),
                        "\"Functional Programming Principles in Scala\" by Martin Odersky"),
                new Organization(
                        "Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        LocalDate.of(2011, 3, 1),
                        LocalDate.of(2011, 4, 1),
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML."),
                new Organization(
                        "Siemens AG",
                        "http://www.siemens.ru/",
                        LocalDate.of(2005, 1, 1),
                        LocalDate.of(2005, 4, 1),
                        "3 месяца обучения мобильным IN сетям (Берлин)")
        )));
        resume.getContacts().forEach((k, v) -> System.out.println(k.getTitle() + "  : " + v));
        System.out.println();
        resume.getSections().forEach((k, v) -> System.out.println("key : " + k.getTitle() + "\n" + "value : " + v));
    }
}

