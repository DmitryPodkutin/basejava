package ru.javawebinar.basejava.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fulName;

    public Resume(String fulName) {
        this(UUID.randomUUID().toString(), fulName);
    }

    public Resume(String uuid, String fulName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fulName, "fulName must not be null");
        this.uuid = uuid;
        this.fulName = fulName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFulName() {
        return fulName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fulName.equals(resume.fulName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fulName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + " " + fulName;
    }

    @Override
    public int compareTo(Resume o) {
        int tmp = fulName.compareTo(o.getFulName());
        return tmp != 0 ? tmp : uuid.compareTo(o.getUuid());  //<если истина> ? <return выражени 1>:<иначе return выражение2>
    }
}
