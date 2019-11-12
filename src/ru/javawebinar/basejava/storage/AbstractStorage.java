package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract public void doUpdate(Object searchKey, Resume resume);

    abstract public void doSave(Object searchKey, Resume resume);

    abstract public Resume doGet(Object searchKey);

    abstract public void doDelete(Object searchKey);

    abstract Object getSearchKey(String uuid);

    protected abstract boolean keyNotExist(Object key);

    public void update(Resume resume) {
        Object searchKey = getNotExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);

    }

    public void save(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    public Resume get(String uuid) {
        return doGet(getNotExistSearchKey(uuid));

    }

    public void delete(String uuid) {
        doDelete(getNotExistSearchKey(uuid));
    }

    public Object getExistSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (!keyNotExist(key)) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    public Object getNotExistSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (keyNotExist(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }

    }

}



