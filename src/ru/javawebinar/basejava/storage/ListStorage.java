package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static List<Resume> list = new ArrayList<>();

    public List<Resume> getList() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            list.set(getIndex(resume.getUuid()), resume);
        }
    }

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        list.add(resume);
    }

    @Override
    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return list.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            list.remove(getIndex(uuid));
        }

    }

    public Resume[] getAll() {
        Resume storage[] = list.toArray(new Resume[list.size()]);
        int size = storage.length;
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}