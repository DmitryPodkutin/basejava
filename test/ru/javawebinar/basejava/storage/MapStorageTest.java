package ru.javawebinar.basejava.storage;

import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test()
    public void saveOverflow() {
    }
}


