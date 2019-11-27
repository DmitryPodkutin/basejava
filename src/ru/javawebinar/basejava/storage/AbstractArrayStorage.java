package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
     int size = 0;

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Object searchKey, Resume resume) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    public void doSave(Object searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            insertElement(resume, (Integer) searchKey);
            size++;
        }
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    public void doDelete(Object searchKey) {
        fillDeletedElement((Integer) searchKey);
        size--;
        storage[size] = null;
    }

    @Override
    public List<Resume> getListStorage() {
        return Arrays.stream(storage)
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
