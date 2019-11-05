package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void doUpdate(int index, Resume resume) {
        list.set(index, resume);
    }

    @Override
    public void doSave(int index, Resume resume) {
        list.add(resume);
    }

    @Override
    public Resume doGet(int index) {
        return list.get(index);
    }

    @Override
    public void doDelete(int index, String uuid) {
        list.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
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