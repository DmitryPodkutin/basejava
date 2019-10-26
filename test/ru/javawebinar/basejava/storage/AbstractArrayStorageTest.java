package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuidForTest";
    private Resume resume1 = new Resume(UUID_1);
    private Resume resume2 = new Resume(UUID_2);
    private Resume resume3 = new Resume(UUID_3);
    private Resume resume4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        checkSize(4);
        storage.get("uuidForTest");
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExists() throws Exception {
        storage.save(resume2);
    }

    @Test(expected = StorageException.class)
    public void saveOverflou() {
        int size = AbstractArrayStorage.STORAGE_LIMIT;
        for (int i = 4; i <= size + 1; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete("uuid2");
        checkSize(2);
        storage.get("uuid2");
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
        storage.update(storage.get("uuid1"));
        storage.update(storage.get("uuid2"));
        storage.update(storage.get("uuid3"));
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
        storage.get("uuid1");
        storage.get("uuid2");
        storage.get("uuid2");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] all = storage.getAll();
        Assert.assertEquals(all[0], resume1);
        Assert.assertEquals(all[1], resume2);
        Assert.assertEquals(all[2], resume3);
    }

    public void checkSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

}