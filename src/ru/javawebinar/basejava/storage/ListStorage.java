package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void doUpdate(Object searchKey, Resume resume) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    public void doSave(Object searchKey, Resume resume) {
        list.add(resume);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    public void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    public List<Resume> getListStorage() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

}

