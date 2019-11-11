package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test(expected = AssertionError.class)
    public void delete() throws Exception {
        storage.delete("uuid1");
        checkSize(2);
        Assert.assertTrue(UUID_1 == null);
    }

    @Override
    @Test()
    public void saveOverflow() {
    }
}


