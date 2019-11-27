package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUUIDStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(Object searhKey) {
        return (mapStorage.containsKey(searhKey));
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
        ArrayList<Resume> list = new ArrayList<>(mapStorage.values());
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}


