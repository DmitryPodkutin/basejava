package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUUIDStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(Object searchKey) {
        return (mapStorage.containsKey(searchKey));
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public void doUpdate(Object searchKey, Resume resume) {
        mapStorage.put((String) searchKey, resume);
    }

    @Override
    public void doSave(Object searchKey, Resume resume) {
        mapStorage.put((String) searchKey, resume);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    public void doDelete(Object searchKey) {
        mapStorage.remove(searchKey);
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


