package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapValueStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
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
        return mapStorage.get(((Resume) resume).getUuid());
    }

    @Override
    public void doDelete(Object resume) {
        mapStorage.remove(((Resume) resume).getUuid(), resume);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = mapStorage.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}

