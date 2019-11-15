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

    protected abstract boolean isExist(Object searhKey);

    public void update(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);

    }

    public void save(Resume resume) {
        Object searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);

    }

    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public Object getExistSearchKey(String uuid) {
        Object searhKey = getSearchKey(uuid);
        if (!isExist(searhKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searhKey;
        }
    }

    public Object getNotExistSearchKey(String uuid) {
        Object searhKey = getSearchKey(uuid);
        if (isExist(searhKey)) {
            throw new ExistStorageException (uuid);
        } else {
            return searhKey;
        }

    }

}



