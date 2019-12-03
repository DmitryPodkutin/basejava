package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public void doUpdate(Resume searchKey, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public void doSave(Resume searchKey, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    public void doDelete(Resume resume) {
        mapStorage.remove((resume).getUuid(), resume);
    }

    @Override
    public List<Resume> getListStorage() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}

