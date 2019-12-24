package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory mast be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected abstract void doWrite(OutputStream os, Resume resume) throws IOException;

    protected File[] getListFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("No files in directory", null);
        }
        return files;
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("File Write Error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't creat file", file.getAbsolutePath(), e);
        }
        doUpdate(file, resume);
    }

    @Override
    public Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read Error", file.getName(), e);
        }

    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File not delete ", file.getName());
        }
    }

    @Override
    File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getListStorage() {
        List<Resume> list = new ArrayList<>();
        for (File fl : getListFiles()) {
            list.add(doGet(fl));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File myFile : getListFiles()) {
            doDelete(myFile);
        }
    }

    @Override
    public int size() {
        return getListFiles().length;
    }
}
