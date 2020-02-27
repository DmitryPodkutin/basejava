package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = Config.get().getStorageDir();
    final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        ResumeTestData rs = new ResumeTestData();
        RESUME_1 = rs.getResume(UUID_1, "Name1");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_4 = new Resume(UUID_4, "Name4");

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
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void update() {
        ResumeTestData rs = new ResumeTestData();
        Resume resumeForUpdate = rs.getResume(UUID_1, "TestName");
        resumeForUpdate.addContact(ContactType.EMAIL, "test@mail.com");
        resumeForUpdate.addContact(ContactType.SKYPE, "TestSkype");
        resumeForUpdate.addContact(ContactType.TEL, "123456789");
        storage.update(resumeForUpdate);
        assertEquals(resumeForUpdate, storage.get(UUID_1));

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void size() {
        checkSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
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