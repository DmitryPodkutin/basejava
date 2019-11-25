package ru.javawebinar.basejava.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    String fulName;

    public Resume(String uuid, String fulName) {
        this.uuid = uuid;
        this.fulName = fulName;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
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

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid + " " + fulName;
    }

    @Override
    public int compareTo(Resume o) {
        int tmp = fulName.compareTo(o.getFulName());
        if (tmp == 0) {
            return uuid.compareTo(o.getUuid());
        } else {
            return tmp;
        }
    }
}
