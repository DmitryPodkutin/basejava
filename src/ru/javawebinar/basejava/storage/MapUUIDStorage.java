package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUUIDStorage extends AbstractStorage<String> {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(String searchKey) {
        return (mapStorage.containsKey(searchKey));
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public void doUpdate(String searchKey, Resume resume) {
        mapStorage.put(searchKey, resume);
    }

    @Override
    public void doSave(String searchKey, Resume resume) {
        mapStorage.put(searchKey, resume);
    }

    @Override
    public Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    public void doDelete(String searchKey) {
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


