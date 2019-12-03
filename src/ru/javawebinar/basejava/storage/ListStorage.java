package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void doUpdate(Integer searchKey, Resume resume) {
        list.set(searchKey, resume);
    }

    @Override
    public void doSave(Integer searchKey, Resume resume) {
        list.add(resume);
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    public void doDelete(Integer searchKey) {
        list.remove((searchKey).intValue());
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

