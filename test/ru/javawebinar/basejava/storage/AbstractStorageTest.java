package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Resume resume1 = new Resume(UUID_1, "Ivan");
    private final Resume resume2 = new Resume(UUID_2, "Anrey");
    private final Resume resume3 = new Resume(UUID_3, "Dima");
    private final Resume resume4 = new Resume(UUID_4, "Anton");

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume2);
        storage.save(resume1);
        storage.save(resume3);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        checkSize(4);
        storage.get(UUID_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(resume2);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        checkSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume resumeForUpdate = new Resume(UUID_1, "Vasi");
        storage.update(resumeForUpdate);
        assertEquals(resumeForUpdate, storage.get(UUID_1));

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(storage.get("dummy"));
    }

    @Test
    public void size() throws Exception {
        checkSize(3);
    }

    @Test
    public void get() throws Exception {
        storage.get(UUID_1);
        storage.get(UUID_2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resumeActual = storage.getAllSorted();
        ArrayList<Resume> resumeExpected = new ArrayList<>();
        resumeExpected.add(resume1);
        resumeExpected.add(resume2);
        resumeExpected.add(resume3);
        assertEquals(3, resumeActual.size());
        Assert.assertEquals(resumeExpected, resumeActual);
    }

    private void checkSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

}