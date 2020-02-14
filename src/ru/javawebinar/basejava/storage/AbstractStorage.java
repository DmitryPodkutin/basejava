package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getListStorage();

    public void update(Resume resume) {
        //LOG.warning("Update " + resume);
        SK searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);

    }

    public void save(Resume resume) {
      //  LOG.warning("Save " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    public Resume get(String uuid) {
       // LOG.warning("Get " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);

    }

    public List<Resume> getAllSorted() {
      //  LOG.warning("getAllSorted ");
        List<Resume> list = getListStorage();
        Collections.sort(list);
        return list;
    }

    public void delete(String uuid) {
      //  LOG.warning("Delete " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
       //     LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
        //    LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }

    }

}



