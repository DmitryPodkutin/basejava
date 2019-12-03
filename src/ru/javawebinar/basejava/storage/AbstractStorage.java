package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getListStorage();

    public void update(Resume resume) {
        SK searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);

    }

    public void save(Resume resume) {
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);

    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getListStorage();
        list.sort(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFulName));
        return list;
    }

    public void delete(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }

    }

}



