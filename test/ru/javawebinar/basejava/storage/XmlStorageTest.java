package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.XmlStreamSerializer;

public class XmlStorageTest extends AbstractStorageTest {

    public XmlStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new XmlStreamSerializer()));
    }
}
