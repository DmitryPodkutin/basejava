package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " can't be update because it already exist");
        }

    }

    public void save(Resume resume) {
        if (indexOf(resume.getUuid()) > -1) {
            System.out.println("Resume " + resume.getUuid() + " can't be saved because it already exists");
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (indexOf(uuid) > -1) {
            return storage[indexOf(uuid)];
        }
        System.out.println("Resume " + uuid + " can't be get because it doesn't exist");
        return null;
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index > -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " can't be deleted because it doesn't exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size + 2);
    }

    public int size() {
        return size;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
