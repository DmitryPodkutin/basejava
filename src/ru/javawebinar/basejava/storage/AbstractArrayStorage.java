package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected boolean checkKey(Object key) {
        return (Integer) key < 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void doSave(Object index, Resume resume) {
        if (size == storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            insertElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        size--;
        storage[size] = null;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }


    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
