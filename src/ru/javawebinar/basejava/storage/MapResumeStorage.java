package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public void doUpdate(Object searchKey, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public void doSave(Object searchKey, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    public void doDelete(Object resume) {
        mapStorage.remove(((Resume) resume).getUuid(), resume);
    }

    @Override
    public List<Resume> getListStorage() {
        ArrayList<Resume> list = new ArrayList<>(mapStorage.values());
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}

