package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
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
    public List<Resume> getAllSorted() {
        List<Resume> list = mapStorage.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}


