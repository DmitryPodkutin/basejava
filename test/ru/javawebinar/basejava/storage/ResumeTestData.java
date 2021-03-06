package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.UUID;

public class ResumeTestData extends Resume{
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Grigory Kislin");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_2 = new Resume(UUID_2, "Dmitry Podkutin");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.addContact(ContactType.MOBILE, "+7(921) 855-0482");
        RESUME_1.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_1.addContact(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.addContact(ContactType.GIT_HUB, "https://github.com/gkislin");
        RESUME_1.addContact(ContactType.STACKOVER_FLOW, "https://stackoverflow.com/users/548473");
        RESUME_1.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
        RESUME_1.addSection(SectionType.OBJECTIVE, new DescriptionSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        RESUME_1.addSection(SectionType.PERSONAL, new DescriptionSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.", "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С," +
                        " Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера.")));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle")));
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects", "http://javaops.ru/",
                                new Organization.Position(DateUtil.of(2013, Month.SEPTEMBER),
                                        LocalDate.now(),
                                        "Автор проекта.",
                                        "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                        new Organization("Wrike", "https://www.wrike.com/",
                                new Organization.Position(DateUtil.of(2011, Month.SEPTEMBER),
                                        DateUtil.of(2016, Month.JANUARY),
                                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                        new Organization("RIT Center",
                                new Organization.Position(DateUtil.of(2012, Month.APRIL),
                                        DateUtil.of(2014, Month.SEPTEMBER),
                                        "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), \" +\n" +
                                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. \" +\n" +
                                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). \" +\n" +
                                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, \" +\n" +
                                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))
                ));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Coursera", "https://www.coursera.org/course/progfun",
                                new Organization.Position(DateUtil.of(2013, Month.MARCH),
                                        DateUtil.of(2013, Month.APRIL),
                                        "Functional Programming Principles in Scala\" by Martin Odersky")),
                        new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                                new Organization.Position(DateUtil.of(2011, Month.MARCH),
                                        DateUtil.of(2011, Month.APRIL), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")),
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                                new Organization.Position(DateUtil.of(1993, Month.SEPTEMBER), DateUtil.of(1996, Month.JULY), "Аспирантура (программист С, С++)"),
                                new Organization.Position(DateUtil.of(1987, Month.JULY),
                                        DateUtil.of(1993, Month.JULY),
                                        "Инженер (программист Fort ran, C)"))
                ));

        RESUME_2.addContact(ContactType.MOBILE, "+7(473) 295-23-21");
        RESUME_2.addContact(ContactType.SKYPE, "di.vrn");
        RESUME_2.addContact(ContactType.EMAIL, "dmitry.podkutin@gmail.com");
        RESUME_2.addContact(ContactType.GIT_HUB, "https://github.com/DmitryPodkutin");
        RESUME_2.addSection(SectionType.OBJECTIVE, new DescriptionSection("Java-разработчик веб-приложений"));
        RESUME_2.addSection(SectionType.PERSONAL, new DescriptionSection("Аналитический склад ума,  логическое мышление, креативность, инициативность. Перфекционизм."));
        RESUME_2.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "01/2019-04/2019 Интенсив для начинающих программистов ‐ StartJava","09/2019-03/2020 Java-разработчик веб-приложений ‐ BaseJava")));
        RESUME_2.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "JEE AS:Tomcat",
                "Version control: Subversion, Git",
                "DB: PostgreSQL")));
    }
}

