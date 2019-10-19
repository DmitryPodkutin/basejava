package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected AbstractArrayStorage() {
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " can't be saved because it already exists");
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            positionForSave(resume, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " can't be deleted because it doesn't exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
            storage[size] = null;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " can't be update because it already exist");
        } else {
            storage[index] = resume;
        }
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("Resume " + uuid + " can't be get because it doesn't exist");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void positionForSave(Resume resume, int index);
}
