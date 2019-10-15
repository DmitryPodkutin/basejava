package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (indexOfArray(r.getUuid()) > -1) {
            storage[indexOfArray(r.getUuid())] = r;
        } else {
            System.out.println("Resume " + r.getUuid() + " can't be update because it already exist");
        }

    }

    public void save(Resume r) {
        if (size < storage.length & indexOfArray(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            System.out.println("Resume " + r.getUuid() + " can't be save because it already exist");
        }
    }

    public Resume get(String uuid) {

        if (indexOfArray(uuid) > -1) {
            return storage[indexOfArray(uuid)];
        } else {
            System.out.println("Resume " + uuid + " can't be get because it doesn't exist");
            return null;
        }
    }

    public void delete(String uuid) {
        int i = indexOfArray(uuid);
        if (i > -1) {
            System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
            size--;
        } else {
            System.out.println("Resume " + uuid + " can't be deleted because it doesn't exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    int indexOfArray(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
