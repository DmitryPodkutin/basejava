package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    public abstract void clear();

    abstract public void update(Resume resume);

    abstract public void save(Resume resume);

    abstract public Resume get(String uuid);

    abstract public void delete(String uuid);

    abstract public Resume[] getAll();

    abstract public int size();

    abstract int getIndex(String uuid);

}


