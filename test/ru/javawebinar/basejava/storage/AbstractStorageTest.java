package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import util.DateUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File("/Users/Dmitry/Documents/JavaLessons/basejava/storage");
    final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.getContacts().put(ContactType.TEL, "+7(921) 855-0482");
        RESUME_1.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_1.getContacts().put(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.getContacts().put(ContactType.GIT_HUB, "https://github.com/gkislin");
        RESUME_1.getContacts().put(ContactType.STACKOVER_FLOW, "https://stackoverflow.com/users/548473");
        RESUME_1.getContacts().put(ContactType.HOME_PAGE, "http://gkislin.ru/");
        RESUME_1.getSections().put(SectionType.OBJECTIVE, new DescriptionSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        RESUME_1.getSections().put(SectionType.PERSONAL, new DescriptionSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_1.getSections().put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                        "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.", "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.", "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С," +
                        " Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера.")));
        RESUME_1.getSections().put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle")));

        RESUME_2.getContacts().put(ContactType.TEL, "+7(921) 855-0482");
        RESUME_2.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        RESUME_2.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_2.getContacts().put(ContactType.LINKED_IN, "https://www.linkedin.com/in/gkislin");
        RESUME_2.getContacts().put(ContactType.GIT_HUB, "https://github.com/gkislin");
        RESUME_2.getContacts().put(ContactType.STACKOVER_FLOW, "https://stackoverflow.com/users/548473");
        RESUME_2.getContacts().put(ContactType.HOME_PAGE, "http://gkislin.ru/");
        RESUME_2.getSections().put(SectionType.EXPERIENCE,
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
                                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))
                ));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_1);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        checkSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_2);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        checkSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void update() {
        Resume resumeForUpdate = new Resume(UUID_1, "Name1");
        storage.update(resumeForUpdate);
        assertEquals(resumeForUpdate, storage.get(UUID_1));

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get("dummy"));
    }

    @Test
    public void size() {
        checkSize(3);
    }

    @Test
    public void get() {
        storage.get(UUID_1);
        storage.get(UUID_2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeActual = storage.getAllSorted();
        List<Resume> resumeExpected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(3, resumeActual.size());
        Assert.assertEquals(resumeExpected, resumeActual);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void checkSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

}