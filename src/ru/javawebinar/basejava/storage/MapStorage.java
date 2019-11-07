package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends ArrayStorage {
    private static Map<String, Resume> hashMap = new HashMap<>();

    @Override
    protected int getIndex(String uuid) {
        for (Resume value : hashMap.values()) {
            if (value.getUuid().equals(uuid)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public void doUpdate(int index, Resume resume, String uuid) {
        hashMap.put(uuid, resume);
    }

    @Override
    public void doSave(int index, Resume resume, String uuid) {
        hashMap.put(uuid, resume);
    }

    @Override
    public Resume doGet(int index, String uuid) {
        return hashMap.get(uuid);
    }

    public void doDelete(int index, String uuid) {
        hashMap.remove(uuid);
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


