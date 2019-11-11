package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private static Map<String, Resume> hashMap = new HashMap<>();

    @Override
    protected String getIndex(String uuid) {
        for (String key : hashMap.keySet()) {
            if (key.equals(uuid)) {
                return null;
            }
        }
         return  uuid;
    }

    @Override
    protected boolean checkKey(Object key) {
        return key != null;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public void doUpdate(Object index, Resume resume) {
        hashMap.put((String) index, resume);
    }

    @Override
    public void doSave(Object index, Resume resume) {
        hashMap.put((String) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return hashMap.get(index);
    }

    @Override
    public void doDelete(Object index) {
        hashMap.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return hashMap.values().toArray(new Resume[0]);

    }

    @Override
    public int size() {
        return hashMap.size();
    }
}


