package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    public abstract void clear();

    abstract public void doUpdate(Resume resume);

    abstract public void save(Resume resume);

    abstract public Resume doGet(String uuid);

    abstract public void delete(String uuid);

    abstract public Resume[] getAll();

    abstract public int size();

    abstract int getIndex(String uuid);

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume);
        }
    }

}



