package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    abstract public void doUpdate(Object index, Resume resume);

    abstract public void doSave(Object index, Resume resume);

    abstract public Resume doGet(Object index);

    abstract public void doDelete(Object index);

    abstract public Resume[] getAll();

    abstract public int size();

    abstract Object getIndex(String uuid);

    protected abstract boolean checkKey(Object key);

    public void update(Resume resume) {
        doUpdate(checkNoteExist(resume.getUuid()), resume);

    }

    public void save(Resume resume) {
        doSave(checkExist(resume.getUuid()), resume);
    }

    public Resume get(String uuid) {
        return doGet(checkNoteExist(uuid));

    }

    public void delete(String uuid) {
        doDelete(checkNoteExist(uuid));
    }

    public Object checkExist(String uuid) {
        Object key = getIndex(uuid);
        if (!checkKey(key)) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    public Object checkNoteExist(String uuid) {
        Object key = getIndex(uuid);
        if (checkKey(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }

    }

}



