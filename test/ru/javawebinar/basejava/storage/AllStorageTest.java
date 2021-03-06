package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListExpirenceStorageTest.class,
        MapUUIDStorageTest.class,
        MapResumeStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        XmlStorageTest.class,
        JsonStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}
