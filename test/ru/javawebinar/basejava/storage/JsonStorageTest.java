package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.JsonStreamSerializer;

public class JsonStorageTest extends AbstractStorageTest {

    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new JsonStreamSerializer()));
    }
}
